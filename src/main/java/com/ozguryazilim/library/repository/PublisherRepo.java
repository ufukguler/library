package com.ozguryazilim.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ozguryazilim.library.entity.Publisher;

import javax.transaction.Transactional;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    @Modifying
    @Transactional
    @Query(value = "update publisher set name= :name,comment= :comment where id = :id", nativeQuery = true)
    int updatePublisher(Long id,String name, String comment);
}
