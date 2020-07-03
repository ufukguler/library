package com.ozguryazilim.library.controller;

import com.ozguryazilim.library.entity.User;
import com.ozguryazilim.library.entity.UserRole;
import com.ozguryazilim.library.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/register")
    public String show(){
        return "register";
    }
    @PostMapping("register")
    public String getBook(User user){
        User newUser = user;
        User control = userRepo.findByUsername(newUser.getUsername());

        // yeni kaydin username ve mail degerleri veri tabaninda olmamali
        if(control == null) {
            String newPass = new BCryptPasswordEncoder().encode(newUser.getPassword());
            newUser.setPassword(newPass);
            userRepo.save(newUser);
        }
        return "register";
    }
}

