package com.spring.library.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
@EntityListeners(AuditingEntityListener.class)
public class Book extends EntityBase {

    @Column(name = "TITLE", length = 255)
    private String title;

    @Column(name = "ALT", length = 255)
    private String alt;

    @Column(name = "SERIES", length = 255)
    private String series;

    @Column(name = "ISBN", length = 255, unique = true)
    private Long isbn;

    @Column(name = "COMMENT", length = 255)
    private String comment;

    // 1->n author-book
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    @JsonManagedReference
    private Author author;

    // 1->n publisher-book
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUBLISHER_ID", nullable = false)
    @JsonManagedReference
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, String alt, String series, Long isbn, String comment, Author author, Publisher publisher) {
        this.title = title;
        this.alt = alt;
        this.series = series;
        this.isbn = isbn;
        this.comment = comment;
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", alt='" + alt + '\'' +
                ", series='" + series + '\'' +
                ", isbn=" + isbn +
                ", comment='" + comment + '\'' +
                ", author=" + author +
                ", publisher=" + publisher +
                '}';
    }
}

