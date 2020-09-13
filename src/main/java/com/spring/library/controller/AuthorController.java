package com.spring.library.controller;

import com.spring.library.model.AuthorDTO;
import com.spring.library.model.AuthorUpdateDTO;
import com.spring.library.repository.AuthorRepo;
import com.spring.library.repository.BookRepo;
import com.spring.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {

    BookRepo bookRepo;
    @Autowired
    AuthorRepo authorRepo;
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService, BookRepo bookRepo) {
        this.authorService = authorService;
        this.bookRepo = bookRepo;
    }

    @GetMapping("/authors")
    public String getAll(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "authors";
    }

    @GetMapping("/authors/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newAuthor() {
        return "newAuthor";
    }

    @PostMapping("authors/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newAuthor(AuthorDTO author) {
        authorService.create(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editAuthor(Model model, @PathVariable(value = "id") Long id) {
        model = authorService.editAuthor(model, id);
        return "editAuthor";
    }

    @PostMapping("/authors/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateAuthor(AuthorUpdateDTO authorUpdateDTO, @PathVariable(value = "id") Long id) {
        authorService.updateAuthor(authorUpdateDTO);
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteAuthor(@PathVariable(value = "id") Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

}

