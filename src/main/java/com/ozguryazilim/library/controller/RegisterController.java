package com.ozguryazilim.library.controller;

import com.ozguryazilim.library.model.User;
import com.ozguryazilim.library.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class RegisterController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String registerPost(User user, RedirectAttributes redirectAttributes) {

        String username = user.getUsername();
        String mail = user.getMail();
        Optional<User> checkUser = userRepo.findByUsername(username);
        Optional<User> checkMail = userRepo.findByMail(mail);

        // check if username and mail not exist on user table at database
        if ( !checkUser.isPresent() ) {
            if ( !checkMail.isPresent() ) {
                User newUser = new User();
                newUser.setUsername(user.getUsername());
                newUser.setMail(user.getMail());
                newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                newUser.setActive(true);
                newUser.setRoles("ROLE_USER");
                userRepo.save(newUser);
                redirectAttributes.addAttribute("success", "");
                return "redirect:/register";

            } else { //parameter for mail exist
                redirectAttributes.addAttribute("mail", "exist");
                return "redirect:/register";
            }
        } else { //parameter for username error
            redirectAttributes.addAttribute("username", "exist");
            return "redirect:/register";
        }
    }
}

