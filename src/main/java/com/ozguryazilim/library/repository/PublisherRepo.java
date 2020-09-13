package com.ozguryazilim.library.repository;

import com.ozguryazilim.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ozguryazilim.library.entity.Publisher;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByName(String name);

    List<Publisher> findPublishersByActiveTrue();
}
