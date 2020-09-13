package com.spring.library.controller;

import com.spring.library.model.PublisherDTO;
import com.spring.library.repository.BookRepo;
import com.spring.library.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublisherController {

    PublisherService publisherService;
    BookRepo bookRepo;

    @Autowired
    public PublisherController(PublisherService publisherService, BookRepo bookRepo) {
        this.publisherService = publisherService;
        this.bookRepo = bookRepo;
    }

    @GetMapping("/publishers")
    public String getAll(Model model) {
        model.addAttribute("publishers", publisherService.getAll());
        return "publishers";
    }

    @GetMapping("/publishers/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newPublisher() {
        return "newPublisher";
    }

    @PostMapping("publishers/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newPublisher(PublisherDTO publisherDTO) {
        publisherService.create(publisherDTO);
        return "redirect:/publishers";
    }

    @GetMapping("/publishers/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPublisher(Model model, @PathVariable(value = "id") Long id) {
        model = publisherService.editPublisher(model,id);
        return "editPublisher";
    }

    @PostMapping("/publishers/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateAuthor(PublisherDTO publisherDTO, @PathVariable(value = "id") Long id) {
        publisherService.updatePublisher(publisherDTO);
        return "redirect:/publishers";
    }

    @GetMapping("/publishers/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteAuthor(@PathVariable(value = "id") Long id) {
        publisherService.delete(id);
        return "redirect:/publishers";
    }

}
