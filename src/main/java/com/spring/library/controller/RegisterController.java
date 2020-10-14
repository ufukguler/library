package com.spring.library.controller;

import com.spring.library.entity.User;
import com.spring.library.model.UserDTO;
import com.spring.library.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // reigster form post request
    @PostMapping("register")
    public String registerPost(@Valid UserDTO user, RedirectAttributes redirectAttributes) {
        return registerService.registerPost(user, redirectAttributes);
    }
}

