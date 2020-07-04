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

import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    BookRepo bookRepo;

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    PublisherRepo publisherRepo;

    // list all books
    @GetMapping("/books")
    public String showAll(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "books";
    }

    // add book form page
    @GetMapping("/books/new")
    public String newBook(Model model) {
        // send author and publisher list to the "<select>" form
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("publishers", publisherRepo.findAll());
            return "newBook";
    }

    // add new book form post request
    @PostMapping("books/new")
    public String newBook(Book book) {
        // set book author by author id
        book.setAuthor(authorRepo.getOne(Long.valueOf(book.getAuthorId())));
        // set book publisher by publisher id
        book.setPublisher(publisherRepo.getOne(Long.valueOf(book.getPublisherId())));
        // save book to DB
        bookRepo.save(book);
        return "redirect:/books";
    }

    // edit form page
    @GetMapping("/books/edit/{id}")
    public String editBook(Model model, @PathVariable(value = "id") Long id) {
        Optional<Book> book = bookRepo.findById(id);

        // send book's authorId & publisherId
        // this will set the select/option as selected
        model.addAttribute("authorId",book.get().getAuthor().getId().toString());
        model.addAttribute("publisherId",book.get().getPublisher().getId().toString());

        // send selected book to thymeleaf as a model attribute
        model.addAttribute("selectedBook", book);
        // author listing
        model.addAttribute("authors", authorRepo.findAll());
        // publisher listing
        model.addAttribute("publishers", publisherRepo.findAll());
        System.out.println(model.getAttribute("selectedBook"));
        return "editBook";
    }

    // edit book form post request
    @RequestMapping(value = "/books/edit/{id}", method = RequestMethod.POST)
    public String updateBook(Model model, Book book, @PathVariable(value = "id") Long id) {
        // set new author by author id
        book.setAuthor(authorRepo.getOne(Long.valueOf(book.getAuthorId())));
        // set new publisher by publisher id
        book.setPublisher(publisherRepo.getOne(Long.valueOf(book.getPublisherId())));
        // save book
        bookRepo.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        // does book exists check
        bookRepo.deleteById(id);
        boolean isFound = bookRepo.existsById(id);
        if (isFound)  // if deleted send parameter to display result on books page
            redirectAttributes.addAttribute("false", "");
        else
            redirectAttributes.addAttribute("true", "");
        return "redirect:/books";
    }
}
