package com.spring.library.repository;

import com.spring.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByActiveTrue();

    Optional<Author> findAuthorsByIdAndActiveIsTrue(Long id);

}
