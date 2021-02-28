package com.spring.library.controller;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepo;
import com.spring.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserRepo userRepo;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // user settings page
    @GetMapping("/profile")
    public String profile(Model model) {
        userService.profile(model);
        return "profile";
    }

    // edit mail form post request
    @PostMapping("/profile/editEmail")
    public String editMail(Model model, User user, RedirectAttributes redirectAttributes) {
        return userService.editMail(model, user, redirectAttributes);
    }

    // edit password form post request
    @PostMapping("/profile/editPass")
    public String updatePassword(Model model, User user, RedirectAttributes redirectAttributes) {
        userService.updatePassword(model, user, redirectAttributes);
        return "redirect:/profile";
    }
}
