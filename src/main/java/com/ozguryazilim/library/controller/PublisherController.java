package com.ozguryazilim.library.controller;

import com.ozguryazilim.library.model.Book;
import com.ozguryazilim.library.model.Publisher;
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

    // listing
    @GetMapping("/publishers")
    public String showAll(Model model) {
        // send all publishers to thymeleaf
        model.addAttribute("publishers", publisherRepo.findAll());
        return "publishers";
    }

    // add publisher page
    @GetMapping("/publishers/new")
    public String newPublisher() {
        return "newPublisher";
    }

    // add publisher form post
    @PostMapping("publishers/new")
    public String newPublisher(Publisher publisher) {
        // save to DB
        publisherRepo.save(publisher);
        return "redirect:/publishers";
    }

    // edit publisher page
    @GetMapping("/publishers/edit/{id}")
    public String editPublisher(Model model, @PathVariable(value = "id") Long id) {
        // filling out the form with the publisher's information
        model.addAttribute("selectedPublisher", publisherRepo.findById(id));
        return "editPublisher";
    }

    // publisher edit post request
    @PostMapping("/publishers/edit/{id}")
    public String updateAuthor(Model model, Publisher publisher, @PathVariable(value = "id") Long id) {
        //set publisher id by path variable
        publisher.setId(id);
        // update publisher
        publisherRepo.updatePublisher(id, publisher.getName(), publisher.getComment());
        return "redirect:/publishers";
    }

    // delet publisher
    @GetMapping("/publishers/delete/{id}")
    public String deleteAuthor(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        publisherRepo.deleteById(id);
        boolean isFound = publisherRepo.existsById(id);
        if(isFound) // return parameter to show result
            redirectAttributes.addAttribute("false", "");
        else
            redirectAttributes.addAttribute("true", "");
        return "redirect:/publishers";
    }

}
