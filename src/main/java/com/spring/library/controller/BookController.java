package com.spring.library.controller;

import com.spring.library.model.BookDTO;
import com.spring.library.model.BookUpdateDTO;
import com.spring.library.services.AuthorService;
import com.spring.library.services.BookService;
import com.spring.library.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private static final String REDIRECT_BOOKS = "redirect:/books";
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
        return REDIRECT_BOOKS;
    }

    @GetMapping("/books/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editBook(Model model, @PathVariable(value = "id") Long id) {
        bookService.editBook(model, id);
        return "editBook";
    }

    @PostMapping(value = "/books/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateBook(BookUpdateDTO bookUpdateDTO, @PathVariable(value = "id") Long id) {
        bookService.updateBook(bookUpdateDTO);
        return REDIRECT_BOOKS;
    }

    @GetMapping("/books/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBook(@PathVariable(value = "id") long id) {
        bookService.delete(id);
        return REDIRECT_BOOKS;
    }
}
