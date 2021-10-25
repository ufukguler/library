package com.spring.library.services;
/*
  User: Ufuk
  Date: 13.09.2020 22:50
*/

import com.spring.library.entity.Publisher;
import com.spring.library.model.PublisherDTO;
import com.spring.library.repository.BookRepository;
import com.spring.library.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;


    public List<Publisher> getAll() {
        return publisherRepository.findPublishersByActiveTrue();
    }

    public Optional<Publisher> getById(Long id) {
        return publisherRepository.findPublishersByIdAndActiveIsTrue(id);
    }

    public void create(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherDTO.getName());
        publisher.setComment(publisherDTO.getComment());
        publisherRepository.save(publisher);
    }

    public void editPublisher(Model model, Long id) {
        model.addAttribute("selectedPublisher", publisherRepository.findPublishersByIdAndActiveIsTrue(id));
        model.addAttribute("books", bookRepository.findAllByPublisher_IdAndActiveIsTrue(id));
    }

    public Publisher delete(Long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if (!publisher.isPresent()) {
            throw new IllegalArgumentException("publisher not found!");
        }
        publisher.get().setActive(!publisher.get().isActive());
        publisher.get().getBooks().forEach(book -> book.setActive(false));
        return publisherRepository.save(publisher.get());
    }

    public void updatePublisher(PublisherDTO publisherDTO) {
        Optional<Publisher> publisher = publisherRepository.findById(publisherDTO.getId());
        if (!publisher.isPresent()) {
            throw new IllegalArgumentException("publisher not found!");
        }
        publisher.get().setName(publisherDTO.getName());
        publisher.get().setComment(publisherDTO.getComment());
        publisherRepository.save(publisher.get());
    }

}
