package com.spring.library.services;
/*
  User: Ufuk
  Date: 13.09.2020 22:50
*/

import com.spring.library.entity.Publisher;
import com.spring.library.model.PublisherDTO;
import com.spring.library.repository.BookRepo;
import com.spring.library.repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private PublisherRepo publisherRepo;
    private BookRepo bookRepo;

    @Autowired
    public PublisherService(PublisherRepo publisherRepo, BookRepo bookRepo) {
        this.publisherRepo = publisherRepo;
        this.bookRepo = bookRepo;
    }

    public List<Publisher> getAll() {
        return publisherRepo.findPublishersByActiveTrue();
    }

    public Publisher create(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherDTO.getName());
        publisher.setComment(publisherDTO.getComment());
        return publisherRepo.save(publisher);
    }

    public Model editPublisher(Model model, Long id) {
        model.addAttribute("selectedPublisher", publisherRepo.findById(id));
        model.addAttribute("books", bookRepo.findAuthors2(id));
        return model;
    }

    public Publisher delete(Long id) {
        Optional<Publisher> publisher = publisherRepo.findById(id);

        if (!publisher.isPresent())
            throw new IllegalArgumentException("publisher not found!");

        publisher.get().setActive(!publisher.get().isActive());
        return publisherRepo.save(publisher.get());
    }

    public Publisher updatePublisher(PublisherDTO publisherDTO) {
        Optional<Publisher> publisher = publisherRepo.findById(publisherDTO.getId());

        if (!publisher.isPresent())
            throw new IllegalArgumentException("publisher not found!");

        publisher.get().setName(publisherDTO.getName());
        publisher.get().setComment(publisherDTO.getComment());
        return publisherRepo.save(publisher.get());
    }

}
