package com.spring.library.model;
/*
  User: Ufuk
  Date: 13.09.2020 23:35
*/


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookUpdateDTO {
    private Long id;

    @NotBlank
    @Size(max = 255, min = 3, message = "is required")
    private String title;

    @NotBlank
    @Size(max = 255, min = 3, message = "is required")
    private String alt;

    @NotBlank
    @Size(max = 255, min = 3, message = "is required")
    private String series;

    @NotBlank
    @Size(max = 255, min = 3, message = "is required")
    private String comment;

    @NotBlank
    private Long authorId;

    @NotBlank
    private Long publisherId;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }
}
