package com.spring.library.services;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * created by: ufuk on 14.10.2020 11:08
 */

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * user profile page
     * @param model
     * @return user's email address
     */
    public String profile(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails) ? ((UserDetails)principal).getUsername() : principal.toString();
        Optional<User> getUserMail = userRepo.findByUsername(username);
        model.addAttribute("mail",getUserMail.get().getMail());
        return "profile";
    }

    /**
     * update email post request method
     * @param model
     * @param user
     * @param redirectAttributes
     * @return
     */
    public String editMail(Model model, User user, RedirectAttributes redirectAttributes) {

        Optional<User> checkMail = userRepo.findByMail(user.getMail());
        // check if mail exist in DB
        if (!checkMail.isPresent()) {
            Optional<User> currentUser = userRepo.findByUsername(user.getUsername());
            // set user
            user.setId(currentUser.get().getId());
            user.setActive(true);
            user.setMail(user.getMail());
            user.setPassword(currentUser.get().getPassword());
            user.setRoles(currentUser.get().getRoles());
            //update user mail
            userRepo.save(user);
            redirectAttributes.addAttribute("esuccess", "");
            return "redirect:/profile";
        } else {
            redirectAttributes.addAttribute("efail", "");
            return "redirect:/profile";
        }
    }

    /**
     * update password post request method
     * @param model
     * @param user
     * @param redirectAttributes
     * @return
     */
    public String updatePassword(Model model, User user, RedirectAttributes redirectAttributes) {
        Optional<User> currentUser = userRepo.findByUsername(user.getUsername());
        // set user
        user.setId(currentUser.get().getId());
        user.setActive(true);
        user.setMail(currentUser.get().getMail());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(currentUser.get().getRoles());
        //update user password
        userRepo.save(user);
        redirectAttributes.addAttribute("psuccess", "");
        return "redirect:/profile";
    }
}
