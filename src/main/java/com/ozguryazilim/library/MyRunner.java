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


@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private BookRepo BookRepo;

    @Autowired
    private AuthorRepo AuthorRepo;

    @Autowired
    private PublisherRepo PublisherRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String[] args){
        Author author = new Author("Yuval Noah Harari","cultural evolution");
        Publisher pub = new Publisher("Harper","about.");
        Book book = new Book("Sapiens: A Brief History of Humankind","alt","series",9780099590088L,"sapiens",author,pub);

        AuthorRepo.save(author);
        PublisherRepo.save(pub);
        BookRepo.save(book);

        Author author1 = new Author("Arthur C. Clarke","sci-fi");
        Publisher pub1 = new Publisher("Ballantine Books ","about..");
        Book book1 = new Book("Childhood's End","alt","series",9786053755111L,"childhood",author1,pub1);

        AuthorRepo.save(author1);
        PublisherRepo.save(pub1);
        BookRepo.save(book1);

        Author author2 = new Author("Stephen Hawking","Popular Science");
        Publisher pub2 = new Publisher("Bantam Dell Publishing Group","about...");
        Book book2 = new Book("A Brief History of Time","alt","series",9786051067582L,"stephen",author2,pub2);

        AuthorRepo.save(author2);
        PublisherRepo.save(pub2);
        BookRepo.save(book2);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setMail("admin@library.com");
        admin.setActive(true);
        admin.setRoles("ROLE_ADMIN");
        userRepo.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123"));
        user.setMail("user@library.com");
        user.setActive(true);
        user.setRoles("ROLE_USER");
        userRepo.save(user);
    }
}