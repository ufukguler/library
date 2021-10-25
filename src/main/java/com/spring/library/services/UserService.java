package com.spring.library.services;

import com.spring.library.entity.User;
import com.spring.library.model.UserUpdateDto;
import com.spring.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * created by: ufuk on 14.10.2020 11:08
 */

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void profile(Model model){
        model.addAttribute("mail",getCurrentUser().getMail());
    }

    public void editMail(UserUpdateDto userUpdateDto, RedirectAttributes redirectAttributes) {
        Optional<User> checkMail = userRepository.findByMail(userUpdateDto.getMail());
        if (!checkMail.isPresent()) {
            User currentUser = getCurrentUser();
            currentUser.setMail(userUpdateDto.getMail());
            userRepository.save(currentUser);
            redirectAttributes.addAttribute("esuccess", "");
        } else {
            redirectAttributes.addAttribute("efail", "");
        }
    }

    public void updatePassword(UserUpdateDto userUpdateDto, RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser();
        currentUser.setPassword(new BCryptPasswordEncoder().encode(userUpdateDto.getPassword()));
        userRepository.save(currentUser);
        redirectAttributes.addAttribute("psuccess", "");
    }

    @SneakyThrows
    public User getCurrentUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> byMail = userRepository.findByUsername(name);
        return byMail.orElseThrow(() -> new Exception("Kullanıcı bulunamadı."));
    }
}
