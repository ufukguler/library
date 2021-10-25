package com.spring.library.repository;

import com.spring.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByPublisher_IdAndActiveIsTrue(Long id);
    List<Book> findBookByActiveTrue();
    List<Book> findByAuthor_IdAndActiveTrue(Long id);
}
