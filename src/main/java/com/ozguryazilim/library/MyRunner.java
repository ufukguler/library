package com.ozguryazilim.library;


import com.ozguryazilim.library.model.*;
import com.ozguryazilim.library.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
    private UserRoleRepo userRoleRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author("yazar","gerilim romanları yazar");
        Publisher pub = new Publisher("yayin evi","xx yayın evi");

        //String title, String alt, String series, Long isbn, String comment, Author author, Publisher publisher
        Book book = new Book("Sapiens","alt isim","seri",9780099590088L,"aciklama",author,pub);

        AuthorRepo.save(author);
        PublisherRepo.save(pub);
        BookRepo.save(book);

        UserRole userRole = new UserRole("user");
        userRoleRepo.save(userRole);
        UserRole manager = new UserRole("manager");
        userRoleRepo.save(manager);

        User user = new User("user1","ufukglr@yandex.com","password",userRole);
        User user2 = new User("user2","ufukglr@gmail.com","password",manager);

        userRepo.save(user);
        userRepo.save(user2);


    }
}