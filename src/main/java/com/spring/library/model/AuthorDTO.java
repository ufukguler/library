package com.spring.library.model;
/*
  User: Ufuk
  Date: 13.09.2020 21:24
*/
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDTO {

    private Long id;

    @NotBlank
    @Size(max =255, min = 3, message = "lütfen geçerli bir kitap ismi giriniz")
    private String name;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
