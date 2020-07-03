package com.ozguryazilim.library.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String alt;

    private String series;

    private Long isbn;

    private String comment;

    // 1->n author-book
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // 1->n publisher-book
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @Transient
    private String authorId;

    @Transient
    private String publisherId;

    public Book() {
    }

    public Book(Long id, String title, String alt, String series, Long isbn, String comment, Author author, Publisher publisher, String authorId, String publisherId) {
        this.id = id;
        this.title = title;
        this.alt = alt;
        this.series = series;
        this.isbn = isbn;
        this.comment = comment;
        this.author = author;
        this.publisher = publisher;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }

    // main constructor
    public Book(String title, String alt, String series, Long isbn, String comment, Author author, Publisher publisher) {
        this.title = title;
        this.alt = alt;
        this.series = series;
        this.isbn = isbn;
        this.comment = comment;
        this.author = author;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", alt='" + alt + '\'' +
                ", series='" + series + '\'' +
                ", isbn=" + isbn +
                ", comment='" + comment + '\'' +
                ", author=" + author +
                ", publisher=" + publisher +
                ", authorId='" + authorId + '\'' +
                ", publisherId='" + publisherId + '\'' +
                '}';
    }
}

