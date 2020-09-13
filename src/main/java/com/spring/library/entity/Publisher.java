package com.spring.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "publisher")
public class Publisher  extends EntityBase{

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "COMMENT", length = 255)
    private String comment;

    // 1->n book-publisher
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publisher")
    @JsonBackReference
    private Set<Book> books;

    public Publisher() {
    }

    public Publisher(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
