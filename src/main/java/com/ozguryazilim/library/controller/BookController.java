package com.ozguryazilim.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ozguryazilim.library.repository.BookRepo;

@Controller
public class BookController {
    @Autowired
    BookRepo bookRepo;

    @GetMapping("/books")
    public String showAll(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "books";
    }

    @GetMapping("/books/edit/{id}")
    public String getBook(Model model, @PathVariable(value = "id") Long id){
        model.addAttribute("selectedBook",bookRepo.findById(id));
        return "editBook";
    }

    @GetMapping("/books/delete")
    public void deleteBook(@RequestParam long id){
        bookRepo.deleteById(id);
    }

/*
    @GetMapping("/books/update/{id}")
    public String editBook(Model model, ){
        model.addAttribute("editedBook",bookRepo.findById(id));
        return "editBook";
    }
*/

}
