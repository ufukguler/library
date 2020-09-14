package com.spring.library.model;
/*
  User: Ufuk
  Date: 13.09.2020 21:29
*/


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PublisherUpdateDTO {

    @NotBlank
    @Size(max = 255, min = 3, message = "is required")
    private String name;

    @NotBlank
    @Size(max = 255, min = 3, message = "is required")
    private String comment;

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
}
