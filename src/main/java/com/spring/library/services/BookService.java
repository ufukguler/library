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
import com.spring.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public List<Book> getAll() {
        return bookRepository.findBookByActiveTrue();
    }

    public void create(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Optional<Author> author = authorService.getById(bookDTO.getAuthorId());
        Optional<Publisher> publisher = publisherService.getById(bookDTO.getPublisherId());
        if (!author.isPresent()) {
            throw new IllegalArgumentException("author not found");
        }
        if (!publisher.isPresent()) {
            throw new IllegalArgumentException("publisher not found!");
        }
        book.setAuthor(author.get());
        book.setPublisher(publisher.get());
        bookRepository.save(book);
    }

    public void editBook(Model model, Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()){
            throw new IllegalArgumentException("optionalBook not found!");
        }
        model.addAttribute("authorId", optionalBook.get().getAuthor().getId());
        model.addAttribute("publisherId", optionalBook.get().getPublisher().getId());
        model.addAttribute("selectedBook", optionalBook);
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
    }

    public Book delete(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new IllegalArgumentException("author not found!");
        }
        book.get().setActive(!book.get().isActive());
        return bookRepository.save(book.get());
    }

    public Book updateBook(BookUpdateDTO bookUpdateDTO) {
        Optional<Book> book = bookRepository.findById(bookUpdateDTO.getId());
        Optional<Author> author = authorService.getById(bookUpdateDTO.getAuthorId());
        Optional<Publisher> publisher = publisherService.getById(bookUpdateDTO.getPublisherId());
        if (!book.isPresent()) {
            throw new IllegalArgumentException("book not found!");
        }
        if (!author.isPresent()) {
            throw new IllegalArgumentException("author not found!");
        }
        if (!publisher.isPresent()) {
            throw new IllegalArgumentException("publisher not found!");
        }
        book.get().setTitle(bookUpdateDTO.getTitle());
        book.get().setAlt(bookUpdateDTO.getAlt());
        book.get().setComment(bookUpdateDTO.getComment());
        book.get().setSeries(bookUpdateDTO.getSeries());
        book.get().setAuthor(author.get());
        book.get().setPublisher(publisher.get());
        return bookRepository.save(book.get());
    }
}
























