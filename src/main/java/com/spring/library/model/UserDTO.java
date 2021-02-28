package com.spring.library.model;

import javax.validation.constraints.*;

/**
 * created by: ufuk on 14.10.2020 12:21
 */

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
