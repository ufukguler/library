package com.spring.library.repository;

import com.spring.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByName(String name);

    List<Publisher> findPublishersByActiveTrue();
}
