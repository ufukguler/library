package com.ozguryazilim.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ozguryazilim.library.entity.Author;

import javax.transaction.Transactional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

    @Modifying
    @Transactional
    @Query(value = "update author set name= :name,comment= :comment where id = :id", nativeQuery = true)
    int updateAuthor(Long id,String name, String comment);
}
