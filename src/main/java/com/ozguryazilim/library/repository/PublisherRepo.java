package com.ozguryazilim.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ozguryazilim.library.model.Publisher;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {
}
