package com.spring.library.controller;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepo;
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

    // user settings page
    @GetMapping("/profile")
    public String profile(Model model){
        // get current user's username
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails)principal).getUsername() : principal.toString();
        // get email from username
        Optional<User> getUserMail = userRepo.findByUsername(username);
        String email = getUserMail.get().getMail();
        // send mail attr. to thymeleaf
        model.addAttribute("mail",email);
        return "profile";
    }

    // edit mail form post request
    @PostMapping("/profile/editEmail")
    public String editMail(Model model, User user, RedirectAttributes redirectAttributes){

        // check if mail exist in DB
        Optional<User> checkMail = userRepo.findByMail(user.getMail());

        // if not then update it
        if ( !checkMail.isPresent() ){
            Optional<User> currentUser = userRepo.findByUsername(user.getUsername());
            // set user
            user.setId(currentUser.get().getId());
            user.setActive(true);
            user.setMail(user.getMail());
            user.setPassword(currentUser.get().getPassword());
            user.setRoles(currentUser.get().getRoles());
            //update user mail
            userRepo.save(user);
            redirectAttributes.addAttribute("esuccess","");
            return "redirect:/profile";
        }else{
            redirectAttributes.addAttribute("efail","");
            return "redirect:/profile";
        }
    }

    // edit password form post request
    @PostMapping("/profile/editPass")
    public String changePass(Model model, User user, RedirectAttributes redirectAttributes){
        Optional<User> currentUser = userRepo.findByUsername(user.getUsername());
        // set user
        user.setId(currentUser.get().getId());
        user.setActive(true);
        user.setMail(currentUser.get().getMail());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(currentUser.get().getRoles());
        //update user password
        userRepo.save(user);
        redirectAttributes.addAttribute("psuccess","");
        return "redirect:/profile";
    }
}
