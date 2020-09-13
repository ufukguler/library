package com.spring.library.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", length = 255)
    private String username;
    @Column(name = "PASSWORD", length = 255)
    private String password;
    @Column(name = "MAIL", length = 255)
    private String mail;
    @Column(name = "ACTIVE", length = 255)
    private boolean active;
    @Column(name = "ROLES", length = 255)
    private String roles;

    public User(){}

    public User(String username, String password, String mail, boolean active, String roles) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.active = active;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                '}';
    }
}
