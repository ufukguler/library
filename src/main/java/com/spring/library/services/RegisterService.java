package com.spring.library.services;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * created by: ufuk on 14.10.2020 11:01
 */
@Service
public class RegisterService {
    private final UserRepo userRepo;

    @Autowired
    public RegisterService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String registerPost(User user, RedirectAttributes redirectAttributes) {

        // check if these variables are exists at DB
        Optional<User> checkUser = userRepo.findByUsername(user.getUsername());
        Optional<User> checkMail = userRepo.findByMail(user.getMail());

        return userCheck(user, checkUser, checkMail, redirectAttributes);
    }

    private String userCheck(User user, Optional<User> checkUser, Optional<User> checkMail, RedirectAttributes redirectAttributes) {
        if (!checkUser.isPresent()) {
            if (!checkMail.isPresent()) {
                //then create a new user object
                User newUser = new User(user.getUsername(), null, user.getMail(), true, null);
                newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                newUser.setRoles("ROLE_USER");

                userRepo.save(newUser);
                redirectAttributes.addAttribute("success", null);
                return "redirect:/register";

            } else { // if mail exist
                redirectAttributes.addAttribute("mail", "exist");
                return "redirect:/register";
            }
        } else { // if username exist
            redirectAttributes.addAttribute("username", "exist");
            return "redirect:/register";
        }
    }
}
