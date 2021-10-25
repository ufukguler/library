package com.spring.library.controller;

import com.spring.library.model.PublisherDTO;
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
public class PublisherController {
    private final PublisherService publisherService;
    private static final String REDIRECT_PUBLISHERS = "redirect:/publishers";

    @GetMapping("/publishers")
    public String getAll(Model model) {
        model.addAttribute("publishers", publisherService.getAll());
        return "publishers";
    }

    @GetMapping("/publishers/new")
    public String newPublisher() {
        return "newPublisher";
    }

    @PostMapping("publishers/new")
    public String newPublisher(PublisherDTO publisherDTO) {
        publisherService.create(publisherDTO);
        return REDIRECT_PUBLISHERS;
    }

    @GetMapping("/publishers/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPublisher(Model model, @PathVariable(value = "id") Long id) {
        publisherService.editPublisher(model, id);
        return "editPublisher";
    }

    @PostMapping("/publishers/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateAuthor(PublisherDTO publisherDTO, @PathVariable(value = "id") Long id) {
        publisherService.updatePublisher(publisherDTO);
        return REDIRECT_PUBLISHERS;
    }

    @GetMapping("/publishers/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteAuthor(@PathVariable(value = "id") Long id) {
        publisherService.delete(id);
        return REDIRECT_PUBLISHERS;
    }

}
