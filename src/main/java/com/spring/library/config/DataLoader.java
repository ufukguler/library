package com.spring.library.config;

import com.spring.library.entity.User;
import com.spring.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String[] args) {
        if (!userRepository.existsUserByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setMail("admin@library.com");
            user.setRoles("ROLE_ADMIN");
            user.setActive(true);
            user.setPassword(new BCryptPasswordEncoder().encode("123"));
            userRepository.save(user);
            log.info("Saved user : " + user);
        }
        if (!userRepository.existsUserByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setMail("user@library.com");
            user.setRoles("ROLE_USER");
            user.setActive(true);
            user.setPassword(new BCryptPasswordEncoder().encode("123"));
            user.setPassword(new BCryptPasswordEncoder().encode("123"));
            userRepository.save(user);
            log.info("Saved user : " + user);
        }
    }
}