package com.spring.library.config;


import com.spring.library.entity.User;
import com.spring.library.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final UserRepo userRepo;

    @Autowired
    public DataLoader(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String[] args) {

        if (!userRepo.existsUserByUsername("admin")) {
            User admin = new User("admin", null, "admin@libary.com", true, "ROLE_ADMIN");
            admin.setPassword(new BCryptPasswordEncoder().encode("123"));
            userRepo.save(admin);
            logger.info("Saved user : " + admin.toString());
        }
        if (!userRepo.existsUserByUsername("user")) {
            User user = new User("user", null, "user@libary.com", true, "ROLE_USER");
            user.setPassword(new BCryptPasswordEncoder().encode("123"));
            userRepo.save(user);
            logger.info("Saved user : " + user.toString());
        }
    }
}