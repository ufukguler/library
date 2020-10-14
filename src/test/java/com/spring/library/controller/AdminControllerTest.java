package com.spring.library.controller;

import com.spring.library.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * created by: ufuk on 14.10.2020 11:51
 */
@SpringBootTest
class AdminControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    AdminController adminController;

    @Autowired
    AdminService adminService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void adminPageTest() throws Exception {
        mockMvc.perform(get("/admin")
                .with(user("someUser").password("somePassword").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeExists("users", "allusers"));
    }

    @Test
    void adminPageFailTest() throws Exception {
        mockMvc.perform(get("/admin")
                .with(user("someUser").password("somePassword").roles("USER")))
                .andExpect(status().is4xxClientError());
    }

}