package com.ozguryazilim.library.repository;

import com.ozguryazilim.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

    List<Author> findAuthorsByActiveTrue();
}
