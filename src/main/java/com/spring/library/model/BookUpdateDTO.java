package com.spring.library.model;
/*
  User: Ufuk
  Date: 13.09.2020 23:35
*/


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
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
}
