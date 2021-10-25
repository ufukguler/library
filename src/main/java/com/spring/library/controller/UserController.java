package com.spring.library.controller;

import com.spring.library.model.UserUpdateDto;
import com.spring.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public String profile(Model model) {
        userService.profile(model);
        return "profile";
    }

    @PostMapping("/profile/editEmail")
    public String editMail(UserUpdateDto userUpdateDto, RedirectAttributes redirectAttributes) {
        userService.editMail(userUpdateDto, redirectAttributes);
        return "redirect:/profile";
    }

    @PostMapping("/profile/editPass")
    public String updatePassword(UserUpdateDto userUpdateDto, RedirectAttributes redirectAttributes) {
        userService.updatePassword(userUpdateDto, redirectAttributes);
        return "redirect:/profile";
    }
}
