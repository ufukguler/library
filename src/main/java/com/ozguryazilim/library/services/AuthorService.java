package com.ozguryazilim.library.services;
/*
  User: Ufuk
  Date: 13.09.2020 21:26
*/

import com.ozguryazilim.library.entity.Author;
import com.ozguryazilim.library.model.AuthorDTO;
import com.ozguryazilim.library.model.AuthorUpdateDTO;
import com.ozguryazilim.library.repository.AuthorRepo;
import com.ozguryazilim.library.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    public List<Author> getAll() {
        return authorRepo.findAuthorsByActiveTrue();
    }

    public Author create(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setComment(authorDTO.getComment());
        return authorRepo.save(author);
    }

    public Model editAuthor(Model model, Long id) {
        model.addAttribute("selectedAuthor", authorRepo.findById(id));
        model.addAttribute("id", authorRepo.findById(id).get().getId());
        model.addAttribute("books", bookRepo.findByAuthorId(id).toArray());
        return model;
    }


    public Author delete(Long id) {
        Optional<Author> author = authorRepo.findById(id);

        if (!author.isPresent())
            throw new IllegalArgumentException("author not found!");

        author.get().setActive(!author.get().isActive());
        return authorRepo.save(author.get());
    }

    public Author updateAuthor(AuthorUpdateDTO authorUpdateDTO) {
        Optional<Author> author = authorRepo.findById(authorUpdateDTO.getId());

        if (!author.isPresent())
            throw new IllegalArgumentException("author not found!");

        author.get().setName(authorUpdateDTO.getName());
        author.get().setComment(authorUpdateDTO.getComment());
        return authorRepo.save(author.get());
    }
}
