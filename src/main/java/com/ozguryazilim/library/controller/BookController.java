package com.ozguryazilim.library.controller;

import com.ozguryazilim.library.entity.Book;
import com.ozguryazilim.library.repository.AuthorRepo;
import com.ozguryazilim.library.repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.ozguryazilim.library.repository.BookRepo;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public String show(Model model) {
        model.addAttribute("newBook", new Book());
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("publishers", publisherRepo.findAll());
        return "newBook";
    }

    @PostMapping("books/new")
    public String getBook(Book book) {
        book.setAuthor(authorRepo.getOne(new Long(book.getAuthorId())));
        book.setPublisher(publisherRepo.getOne(new Long(book.getPublisherId())));
        bookRepo.save(book);
        return "books";
    }

    @GetMapping("/books/edit/{id}")
    public String getBook(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("selectedBook", bookRepo.findById(id));
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("publishers", publisherRepo.findAll());
        return "editBook";
    }

    @RequestMapping(value = "/books/edit/{id}", method = RequestMethod.POST)
    public String updateBook(Model model, Book book, @PathVariable(value = "id") Long id) {
        book.setAuthor(authorRepo.getOne(new Long(book.getAuthorId())));
        book.setPublisher(publisherRepo.getOne(new Long(book.getPublisherId())));
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
