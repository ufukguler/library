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
        Book book2 = new Book("A Brief History of Time","alt","series",9786051067582L,"about...",author2,pub2);

        AuthorRepo.save(author2);
        PublisherRepo.save(pub2);
        BookRepo.save(book2);

        Author author3 = new Author("Halide Edib Adıvar","Novel");
        Publisher pub3 = new Publisher("Can Yayınları","about...");
        Book book3 = new Book("Sinekli Bakkal","The Clown and His Daughter","series",9789750707766L,"halide",author3,pub3);

        AuthorRepo.save(author3);
        PublisherRepo.save(pub3);
        BookRepo.save(book3);

        Author author4 = new Author("Reşat Nuri Güntekin","Novel");
        Publisher pub4 = new Publisher("İnkılap Kitabevi ","about...");
        Book book4 = new Book("Acımak","alt","series",9789751026569L,"reşat nuri",author4,pub4);

        AuthorRepo.save(author4);
        PublisherRepo.save(pub4);
        BookRepo.save(book4);

        Author author5 = new Author("Oğuz Atay","Novel");
        Publisher pub5 = new Publisher("İletişim Yayınları","about...");
        Book book5 = new Book("Tutunamayanlar","alt","series",9789754700114L,"oğuz",author5,pub5);

        AuthorRepo.save(author5);
        PublisherRepo.save(pub5);
        BookRepo.save(book5);


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