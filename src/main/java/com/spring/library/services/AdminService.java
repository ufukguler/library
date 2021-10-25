package com.spring.library.services;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * created by: ufuk on 14.10.2020 10:51
 */

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public void adminPage(Model model) {
        List<User> userList = userRepository.findAll();
        userRepository.findByUsername("admin").ifPresent(userList::remove);
        model.addAttribute("users", userList);
        model.addAttribute("allusers", userRepository.findAll());
    }

    public void changeRole(User userObj, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userRepository.findById(userObj.getId());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setRoles(userObj.getRoles());
            userRepository.save(user);
        }
        redirectAttributes.addAttribute("success", "updated");
    }
}
