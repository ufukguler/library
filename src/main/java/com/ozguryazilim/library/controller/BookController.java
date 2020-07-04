package com.ozguryazilim.library.controller;

import com.ozguryazilim.library.model.Book;
import com.ozguryazilim.library.repository.AuthorRepo;
import com.ozguryazilim.library.repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ozguryazilim.library.repository.BookRepo;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {
    @Autowired
    BookRepo bookRepo;

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    PublisherRepo publisherRepo;

    @GetMapping("/books")
    public String showAll(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "books";
    }

    @GetMapping("/books/new")
    public String newBook(Model model) {
        model.addAttribute("newBook", new Book());
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("publishers", publisherRepo.findAll());
            return "newBook";
    }

    @PostMapping("books/new")
    public String newBook(Book book) {
        book.setAuthor(authorRepo.getOne(Long.valueOf(book.getAuthorId())));
        book.setPublisher(publisherRepo.getOne(Long.valueOf(book.getPublisherId())));
        bookRepo.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String editBook(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("selectedBook", bookRepo.findById(id));
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("publishers", publisherRepo.findAll());
        return "editBook";
    }

    @RequestMapping(value = "/books/edit/{id}", method = RequestMethod.POST)
    public String updateBook(Model model, Book book, @PathVariable(value = "id") Long id) {
        book.setAuthor(authorRepo.getOne(Long.valueOf(book.getAuthorId())));
        book.setPublisher(publisherRepo.getOne(Long.valueOf(book.getPublisherId())));
        bookRepo.save(book);
        model.addAttribute("selectedBook", bookRepo.findById(id));
        model.addAttribute("id", id);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        bookRepo.deleteById(id);
        boolean isFound = bookRepo.existsById(id);
        if (isFound)
            redirectAttributes.addAttribute("false", "");
        else
            redirectAttributes.addAttribute("true", "");
        return "redirect:/books";
    }
}
