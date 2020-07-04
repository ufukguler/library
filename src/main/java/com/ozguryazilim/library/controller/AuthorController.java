package com.ozguryazilim.library.controller;

import com.ozguryazilim.library.model.Author;
import com.ozguryazilim.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ozguryazilim.library.repository.AuthorRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthorController {
    @Autowired
    AuthorRepo authorRepo;

    @GetMapping("/authors")
    public String showAll(Model model) {
        model.addAttribute("authors", authorRepo.findAll());
        return "authors";
    }

    @GetMapping("/authors/new")
    public String newAuthor(Model model) {
        model.addAttribute("newAuthor", new Book());
        return "newAuthor";
    }

    @PostMapping("authors/new")
    public String newAuthor(Author author) {
        authorRepo.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/edit/{id}")
    public String newAuthor(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("selectedAuthor", authorRepo.findById(id));
        model.addAttribute("id", authorRepo.findById(id).get().getId().toString());
        return "editAuthor";
    }

    @PostMapping("/authors/edit/{id}")
    public String updateAuthor(Author author, @PathVariable(value = "id") Long id) {
        author.setId(id);
        authorRepo.updateAuthor(id, author.getName(), author.getComment());
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete/{id}")
    public String deleteAuthor(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        authorRepo.deleteById(id);
        boolean isFound = authorRepo.existsById(id);
        if(isFound)
            redirectAttributes.addAttribute("false", "");
        else
            redirectAttributes.addAttribute("true", "");
        return "redirect:/authors";
    }

}


