package com.spring.library.services;
/*
  User: Ufuk
  Date: 13.09.2020 23:38
*/

import com.spring.library.entity.Author;
import com.spring.library.entity.Book;
import com.spring.library.entity.Publisher;
import com.spring.library.model.BookDTO;
import com.spring.library.model.BookUpdateDTO;
import com.spring.library.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private AuthorService authorService;
    private PublisherService publisherService;
    private BookRepo bookRepo;

    public BookService(AuthorService authorService, PublisherService publisherService, BookRepo bookRepo) {
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.bookRepo = bookRepo;
    }

    @Autowired


    public List<Book> getAll() {
        return bookRepo.findBookByActiveTrue();
    }

    public void create(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAlt(), bookDTO.getSeries(), bookDTO.getIsbn(), bookDTO.getComment(), null, null);
        Optional<Author> author = authorService.getById(bookDTO.getAuthorId());
        Optional<Publisher> publisher = publisherService.getById(bookDTO.getPublisherId());

        if (!author.isPresent())
            throw new IllegalArgumentException("author not found!");
        if (!publisher.isPresent())
            throw new IllegalArgumentException("publisher not found!");

        book.setAuthor(author.get());
        book.setPublisher(publisher.get());
        bookRepo.save(book);
    }

    public Model editBook(Model model, Long id) {
        Optional<Book> book = bookRepo.findById(id);
        model.addAttribute("authorId", book.get().getAuthor().getId());
        model.addAttribute("publisherId", book.get().getPublisher().getId());
        model.addAttribute("selectedBook", book);
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        return model;
    }

    public Book delete(Long id) {
        Optional<Book> book = bookRepo.findById(id);

        if (!book.isPresent())
            throw new IllegalArgumentException("author not found!");

        book.get().setActive(!book.get().isActive());
        return bookRepo.save(book.get());
    }

    public Book updateBook(BookUpdateDTO bookUpdateDTO) {
        Optional<Book> book = bookRepo.findById(bookUpdateDTO.getId());
        Optional<Author> author = authorService.getById(bookUpdateDTO.getAuthorId());
        Optional<Publisher> publisher = publisherService.getById(bookUpdateDTO.getPublisherId());
        if (!book.isPresent())
            throw new IllegalArgumentException("book not found!");
        if (!author.isPresent())
            throw new IllegalArgumentException("author not found!");
        if (!publisher.isPresent())
            throw new IllegalArgumentException("publisher not found!");
        book.get().setTitle(bookUpdateDTO.getTitle());
        book.get().setAlt(bookUpdateDTO.getAlt());
        book.get().setComment(bookUpdateDTO.getComment());
        book.get().setIsbn(bookUpdateDTO.getIsbn());
        book.get().setSeries(bookUpdateDTO.getSeries());
        book.get().setAuthor(author.get());
        book.get().setPublisher(publisher.get());
        return bookRepo.save(book.get());
    }
}
























