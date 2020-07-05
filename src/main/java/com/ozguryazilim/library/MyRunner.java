package com.ozguryazilim.library;


import com.ozguryazilim.library.model.*;
import com.ozguryazilim.library.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private PublisherRepo publisherRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String[] args) {

        // jk rownling - harryPotter
        Author joanneRownling = new Author("J. K. Rowling", "British author");
        authorRepo.save(joanneRownling);

        Publisher bloomsbury = new Publisher("Bloomsbury", "Bloomsbury Publishing plc");
        publisherRepo.save(bloomsbury);

        bookRepo.save(new Book("Philosopher's Stone", "-", "Harry Potter", 9781524721251L, "published in 1997", joanneRownling, bloomsbury));
        bookRepo.save(new Book("Chamber of Secrets", "-", "Harry Potter", 9780807281918L, "published in 1998", joanneRownling, bloomsbury));
        bookRepo.save(new Book("Prisoner of Azkaban", "-", "Harry Potter", 9780807283158L, "published in 1999", joanneRownling, bloomsbury));

        // georgeRRMartin -- gameOfThrones
        Author georgeMartin = new Author("George R. R. Martin", "American novelist");
        authorRepo.save(georgeMartin);

        Publisher bantamSpectra = new Publisher("Bantam Spectra", "Bantam Books/Random House");
        publisherRepo.save(bantamSpectra);

        bookRepo.save(new Book("A Game of Thrones", "-", "A Song of Ice and Fire", 9780008132200L, "published in 1996", georgeMartin, bantamSpectra));
        bookRepo.save(new Book("A Clash of Kings", "-", "A Song of Ice and Fire", 9780006479895L, "published in 1998", georgeMartin, bantamSpectra));
        bookRepo.save(new Book("A Storm of Swords", "-", "A Song of Ice and Fire", 9780002245869L, "published in 2000", georgeMartin, bantamSpectra));

        // lord of the rings
        Author tolkien = new Author("J. R. R. Tolkien", "English writer, poet, philologist");
        authorRepo.save(tolkien);

        Publisher allenUnwin = new Publisher("George Allen & Unwin", "British Publishing Company");
        publisherRepo.save(allenUnwin);

        bookRepo.save(new Book("The Fellowship of the Ring", "-", "The Lord of the Rings", 9788845292248L, "published in 1954", tolkien, allenUnwin));
        bookRepo.save(new Book("The Two Towers", "-", "The Lord of the Rings", 9780261103399L, "published in 1954", tolkien, allenUnwin));
        bookRepo.save(new Book("The Return of the King", "-", "The Lord of the Rings", 9780261103405L, "published in 1955", tolkien, allenUnwin));

        // pre defined admin
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setMail("admin@library.com");
        admin.setActive(true);
        admin.setRoles("ROLE_ADMIN");
        userRepo.save(admin);

        // pre defined user
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123"));
        user.setMail("user@library.com");
        user.setActive(true);
        user.setRoles("ROLE_USER");
        userRepo.save(user);
    }
}