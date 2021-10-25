package com.spring.library.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book extends EntityBase {

    @Column
    private String title;

    @Column
    private String alt;

    @Column
    private String series;

    @Column(unique = true)
    private Long isbn;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    @JsonManagedReference
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUBLISHER_ID", nullable = false)
    @JsonManagedReference
    private Publisher publisher;
}

