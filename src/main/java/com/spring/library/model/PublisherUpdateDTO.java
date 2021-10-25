package com.spring.library.model;
/*
  User: Ufuk
  Date: 13.09.2020 21:29
*/


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PublisherUpdateDTO {

    @NotBlank
    @Size(max = 255, min = 3, message = "is required")
    private String name;

    @NotBlank
    @Size(max = 255, min = 3, message = "is required")
    private String comment;
}
