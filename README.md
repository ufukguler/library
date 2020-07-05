
# Library | Spring Boot
Library  is a spring boot project that you can add a book, author, and publisher and you can manage these records.

## Features
- Login / Register
- Change Password / Email
- Display Book, Author & Publisher
- Display Author's Books & Publisher's Published Books
- Add Book, Author & Publisher
- Delete Book, Author & Publisher _(admin only)_
- Change User Role (User | Admin) _(admin only)_

### Tech
Library uses a couple of open source projects to work properly:
* [Spring Framework](https://spring.io/) - Dependency Injection Container
* [Maven](https://maven.apache.org/) - Project Management Tool
* [MySQL](https://www.mysql.com/) - Relational Database Management System
* [Thymeleaf](https://www.thymeleaf.org/) - Server-Side Java Template Engine
* [Bootstrap](https://getbootstrap.com/) - Front-end Framework 
* [jQuery](https://jquery.com/) - JavaScript Library

### Installation
 - Create MySQL User & Database
```sh
$ CREATE USER 'library'@'localhost' IDENTIFIED BY 'LNoa5eelBYdCJpW6';
$ CREATE DATABASE library CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
$ GRANT ALL PRIVILEGES ON library.* TO 'library'@'localhost';
$ FLUSH PRIVILEGES;
```
### Run
```sh
$ mvn clean spring-boot:run
```

### Predefined Admin & User
```sh
ROLE   login - pass
-------------------
ADMIN  admin - 123
USER   user  - 123
```
### Login Page
[http://localhost:8080/login](http://localhost:8080/login)

---

License
----

MIT


**Free Software**
