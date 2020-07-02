package com.ozguryazilim.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ozguryazilim.library.repository.AuthorRepo;

@Controller
public class AuthorController {
    @Autowired
    AuthorRepo authorRepo;

    @GetMapping("/authors")
    public String showAll(Model model) {
        model.addAttribute("authors", authorRepo.findAll());
        return "authors";
    }

    @GetMapping("/authors/edit/{id}")
    public String editAuthor(Model model, @PathVariable(value = "id") Long id){
        model.addAttribute("selectedAuthor", authorRepo.findById(id));
        return "editAuthor";
    }
/*
    @GetMapping("/authors/{id}")
    public String getBook(Model model, @PathVariable(value = "id") Long id){
        model.addAttribute("selectedAuthor",authorRepo.findById(id));
        return "author";
    }*/
}


