package com.ozguryazilim.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ozguryazilim.library.repository.PublisherRepo;

@Controller
public class PublisherController {
    @Autowired
    PublisherRepo publisherRepo;

    @GetMapping("/publishers")
    public String showAll(Model model) {
        model.addAttribute("publishers", publisherRepo.findAll());
        return "publishers";
    }

    @GetMapping("/publishers/edit/{id}")
    public String getBook(Model model, @PathVariable(value = "id") Long id){
        model.addAttribute("selectedPublisher",publisherRepo.findById(id));
        return "editPublisher";
    }
/*
    @GetMapping("/publishers/{id}")
    public String getPublisher(Model model,@PathVariable(value = "id") Long id) {
        model.addAttribute("selectedPublisher", publisherRepo.findById(id));
        return "publishers";
    }

 */
}
