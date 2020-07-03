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

}
