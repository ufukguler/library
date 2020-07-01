package com.ozguryazilim.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ozguryazilim.library.model.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
