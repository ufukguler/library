package com.spring.library.controller;

import com.spring.library.services.RegisterService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * created by: ufuk on 14.10.2020 12:08
 */
@SpringBootTest
class RegisterControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    RegisterService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void register() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void registerFormTestSuccess() throws Exception {
        mockMvc.perform(post("/register")
                .param("mail", "success@mail.com")
                .param("username", "successUsername")
                .param("password", "successPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?success=registered"))
                .andExpect(view().name("redirect:/register"));
    }

    @Test
    void registerFormTestMailExist() throws Exception {
        mockMvc.perform(post("/register")
                .param("mail", "admin@library.com")
                .param("username", "someRandomUsername")
                .param("password", "someRandomPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?mail=exist"))
                .andExpect(view().name("redirect:/register"));
    }
    @Test
    void registerFormTestUsernameExist() throws Exception {
        mockMvc.perform(post("/register")
                .param("mail", "3@a.com")
                .param("username", "admin")
                .param("password", "somePassword3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?username=exist"))
                .andExpect(view().name("redirect:/register"));
    }

    @AfterAll
    static void afterAll() {

    }
}