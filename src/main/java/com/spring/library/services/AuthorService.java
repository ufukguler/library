package com.spring.library.services;
/*
  User: Ufuk
  Date: 13.09.2020 21:26
*/

import com.spring.library.entity.Author;
import com.spring.library.model.AuthorDTO;
import com.spring.library.model.AuthorUpdateDTO;
import com.spring.library.repository.AuthorRepository;
import com.spring.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public List<Author> getAll() {
        return authorRepository.findAuthorsByActiveTrue();
    }

    public Optional<Author> getById(Long id){
        return  authorRepository.findAuthorsByIdAndActiveIsTrue(id);
    }

    public Author create(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setComment(authorDTO.getComment());
        return authorRepository.save(author);
    }

    public void editAuthor(Model model, Long id) {
        model.addAttribute("selectedAuthor", authorRepository.findById(id));
        model.addAttribute("id", authorRepository.findById(id).get().getId());
        model.addAttribute("books", bookRepository.findByAuthor_IdAndActiveTrue(id).toArray());
    }


    public Author delete(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()){
            throw new IllegalArgumentException("author not found!");
        }
        author.get().setActive(!author.get().isActive());
        author.get().getBooks().forEach(book -> book.setActive(false));
        return authorRepository.save(author.get());
    }

    public Author updateAuthor(AuthorUpdateDTO authorUpdateDTO) {
        Optional<Author> author = authorRepository.findById(authorUpdateDTO.getId());
        if (!author.isPresent()){
            throw new IllegalArgumentException("author not found!");
        }
        author.get().setName(authorUpdateDTO.getName());
        author.get().setComment(authorUpdateDTO.getComment());
        return authorRepository.save(author.get());
    }
}
