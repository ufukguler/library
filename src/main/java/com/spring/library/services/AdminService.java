package com.spring.library.services;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * created by: ufuk on 14.10.2020 10:51
 */

@Service
public class AdminService {
    private UserRepo userRepo;

    @Autowired
    public AdminService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Model adminPage(Model model) {
        List<User> userList = userRepo.findAll();

        // remove admin from list (admin should not demote itself)
        Optional<User> removeAdmin = userRepo.findByUsername("admin");
        userList.remove(removeAdmin.get());
        // send user list
        model.addAttribute("users", userList);
        model.addAttribute("allusers", userRepo.findAll());
        return model;
    }

    public void changeRole(User user, RedirectAttributes redirectAttributes) {
        //get the user to edit
        Optional<User> currentUser = userRepo.findById(Long.valueOf(user.getId()));
        // change role
        currentUser.get().setRoles(user.getRoles());
        // save user
        userRepo.save(currentUser.get());
        redirectAttributes.addAttribute("success", "updated");
    }

}
