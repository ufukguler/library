package com.spring.library.controller;

import com.spring.library.model.UserDTO;
import com.spring.library.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String registerPost(@Valid UserDTO user, RedirectAttributes redirectAttributes) {
        return registerService.registerPost(user, redirectAttributes);
    }
}

