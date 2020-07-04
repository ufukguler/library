package com.ozguryazilim.library.controller;

import com.ozguryazilim.library.model.User;
import com.ozguryazilim.library.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    UserRepo userRepo;

    // change user role page
    @GetMapping("/admin")
    public String admin(Model model){
        // get all users
        List<User> userList = userRepo.findAll();

        // remove admin from list (admin should not demote itself)
        Optional<User> removeAdmin = userRepo.findByUsername("admin");
        userList.remove(removeAdmin.get());
        // send user list
        model.addAttribute("users",userList);
        model.addAttribute("allusers",userRepo.findAll());

        return "admin";
    }
    @PostMapping("admin/changeRole")
    public String changeRole(User user, RedirectAttributes redirectAttributes){
        //get the user to edit
        Optional<User> currentUser = userRepo.findById(Long.valueOf(user.getId()));
        // change role
        currentUser.get().setRoles(user.getRoles());
        // save user
        userRepo.save(currentUser.get());
        redirectAttributes.addAttribute("success","");
        return "redirect:/admin";
    }
}
