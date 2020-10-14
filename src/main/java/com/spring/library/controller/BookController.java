package com.spring.library.controller;

import com.spring.library.entity.Book;
import com.spring.library.model.BookDTO;
import com.spring.library.model.BookUpdateDTO;
import com.spring.library.services.AuthorService;
import com.spring.library.services.BookService;
import com.spring.library.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping("/books")
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books";
    }

    @GetMapping("/books/new")
    public String newBook(Model model) {
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        return "newBook";
    }

    @PostMapping("books/new")
    public String newBook(BookDTO bookDTO) {
        bookService.create(bookDTO);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editBook(Model model, @PathVariable(value = "id") Long id) {
        bookService.editBook(model, id);
        return "editBook";
    }

    @RequestMapping(value = "/books/edit/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateBook(BookUpdateDTO bookUpdateDTO, @PathVariable(value = "id") Long id) {
        bookService.updateBook(bookUpdateDTO);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBook(@PathVariable(value = "id") long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
