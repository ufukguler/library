package com.ozguryazilim.library.controller;

import com.ozguryazilim.library.entity.Book;
import com.ozguryazilim.library.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ozguryazilim.library.repository.PublisherRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PublisherController {
    @Autowired
    PublisherRepo publisherRepo;

    @GetMapping("/publishers")
    public String showAll(Model model) {
        model.addAttribute("publishers", publisherRepo.findAll());
        return "publishers";
    }

    @GetMapping("/publishers/new")
    public String show(Model model) {
        model.addAttribute("newPublisher", new Book());
        return "newPublisher";
    }

    @PostMapping("publishers/new")
    public String getBook(Publisher publisher) {
        publisherRepo.save(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/publishers/edit/{id}")
    public String getBook(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("selectedPublisher", publisherRepo.findById(id));
        return "editPublisher";
    }

    @PostMapping("/publishers/edit/{id}")
    public String updateAuthor(Model model, Publisher publisher, @PathVariable(value = "id") Long id) {
        publisher.setId(id);
        publisherRepo.updatePublisher(id, publisher.getName(), publisher.getComment());
        model.addAttribute("selectedPublisher", publisherRepo.findById(id));
        model.addAttribute("id", publisherRepo.findById(id).get().getId().toString());
        return "redirect:/publishers";
    }

    @GetMapping("/publishers/delete/{id}")
    public String deletePublisher(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        publisherRepo.deleteById(id);
        boolean isFound = publisherRepo.existsById(id);
        if(isFound)
            redirectAttributes.addAttribute("false", "");
        else
            redirectAttributes.addAttribute("true", "");
        return "redirect:/publishers";
    }

}
