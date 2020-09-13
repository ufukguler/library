package com.spring.library.controller;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepo;
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

    // reigster form post request
    @PostMapping("register")
    public String registerPost(User user, RedirectAttributes redirectAttributes) {

        // get username & mail from the form
        String username = user.getUsername();
        String mail = user.getMail();
        // check if these variables are exists at DB
        Optional<User> checkUser = userRepo.findByUsername(username);
        Optional<User> checkMail = userRepo.findByMail(mail);

        // if not exist
        if ( !checkUser.isPresent() ) {
            if ( !checkMail.isPresent() ) {
                //then create a new user object
                User newUser = new User();
                newUser.setUsername(user.getUsername());
                newUser.setMail(user.getMail());
                newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                newUser.setActive(true);
                // set as a regular user
                // admin can change a user's role
                newUser.setRoles("ROLE_USER");
                // save
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

