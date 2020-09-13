package com.spring.library.repository;

import com.spring.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    @Query(value = "select * from book where author_id = :id" , nativeQuery = true)
    List<Book> findByAuthorId(Long id);

    @Query(value = "select * from book where publisher_id = :id" , nativeQuery = true)
    List<Book> findAuthors2(Long id);

    @Query(value = "select * from book where author_id = :id" , nativeQuery = true)
    List<Book> findAuthors(Long id);
}
