package com.ozguryazilim.library.repository;

import com.ozguryazilim.library.entity.Author;
import com.ozguryazilim.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ozguryazilim.library.entity.Book;

import javax.transaction.Transactional;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    @Modifying
    @Transactional
    @Query(value = "update book set title= :title, alt= :alt, series= :series, isbn= :isbn, comment= :comment  where id = :id", nativeQuery = true)
    int updateBook(Long id, String title, String alt, String series, Long isbn, String comment);
}
