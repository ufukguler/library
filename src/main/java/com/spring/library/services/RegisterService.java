package com.spring.library.services;

import com.spring.library.entity.User;
import com.spring.library.model.UserDTO;
import com.spring.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * created by: ufuk on 14.10.2020 11:01
 */
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public String registerPost(UserDTO user, RedirectAttributes redirectAttributes) {
        Optional<User> checkUser = userRepository.findByUsername(user.getUsername());
        Optional<User> checkMail = userRepository.findByMail(user.getMail());
        return userCheck(user, checkUser, checkMail, redirectAttributes);
    }

    private String userCheck(UserDTO userDTO, Optional<User> checkUser, Optional<User> checkMail, RedirectAttributes redirectAttributes) {
        String redirectRegister = "redirect:/register";
        if (userDTO.getMail().isEmpty() || userDTO.getUsername().isEmpty() || userDTO.getPassword().isEmpty()) {
            redirectAttributes.addAttribute("error", "");
        }
        if (!checkUser.isPresent()) {
            if (!checkMail.isPresent()) {
                User newUser = modelMapper.map(userDTO, User.class);
                newUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
                newUser.setRoles("ROLE_USER");
                userRepository.save(newUser);
                redirectAttributes.addAttribute("success", "registered");
            } else {
                redirectAttributes.addAttribute("mail", "exist");
            }
        } else {
            redirectAttributes.addAttribute("username", "exist");
        }
        return redirectRegister;
    }
}
