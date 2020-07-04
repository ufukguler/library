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

    // author listing
    @GetMapping("/authors")
    public String showAll(Model model) {
        model.addAttribute("authors", authorRepo.findAll());
        return "authors";
    }

    // add author page
    @GetMapping("/authors/new")
    public String newAuthor() {
        return "newAuthor";
    }

    // add new author form post request
    @PostMapping("authors/new")
    public String newAuthor(Author author) {
        // save to DB
        authorRepo.save(author);
        return "redirect:/authors";
    }

    // author edit form page
    @GetMapping("/authors/edit/{id}")
    public String newAuthor(Model model, @PathVariable(value = "id") Long id) {
        // filling out the form with the author's information
        model.addAttribute("selectedAuthor", authorRepo.findById(id));
        model.addAttribute("id", authorRepo.findById(id).get().getId().toString());
        return "editAuthor";
    }

    // author edit form post request
    @PostMapping("/authors/edit/{id}")
    public String updateAuthor(Author author, @PathVariable(value = "id") Long id) {
        // get author id from path variable and set author id to update it
        author.setId(id);
        // update author query
        authorRepo.updateAuthor(id, author.getName(), author.getComment());
        return "redirect:/authors";
    }

    // author delete page
    @GetMapping("/authors/delete/{id}")
    public String deleteAuthor(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        // does author exists check
        authorRepo.deleteById(id);
        boolean isFound = authorRepo.existsById(id);
        // send parameter
        // if deleted there will be a notfication as "Deleted." on authors page
        if(isFound)
            redirectAttributes.addAttribute("false", "");
        else
            redirectAttributes.addAttribute("true", "");
        return "redirect:/authors";
    }

}


