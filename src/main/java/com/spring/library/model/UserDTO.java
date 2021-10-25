package com.spring.library.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * created by: ufuk on 14.10.2020 12:21
 */

@Data
public class UserDTO {

    @NotEmpty
    @NotNull
    @Size(max = 255, min = 3, message = "is required")
    private String username;

    @NotEmpty
    @NotNull
    @Size(max = 255, min = 6, message = "is required")
    private String password;

    @Email
    private String mail;
}
