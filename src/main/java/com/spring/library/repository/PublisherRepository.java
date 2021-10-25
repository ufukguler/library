package com.spring.library.repository;

import com.spring.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findPublishersByActiveTrue();

    Optional<Publisher> findPublishersByIdAndActiveIsTrue(Long id);
}
