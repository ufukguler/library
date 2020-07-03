
# Library | Spring Boot
Library  is a spring boot project that you can add a book, author, and publisher and you can manage these records.

## Features
- Add Book, Author & Publisher
- Delete Book, Author & Publisher _(admin only)_
- Display Book, Author & Publisher
- <del>Login / Register</del>
- <del>Change Password / Email</del>

### Tech
Library uses a couple of open source projects to work properly:
* [jQuery](https://jquery.com/) - JavaScript Library
* [Bootstrap](https://getbootstrap.com/) - Front-end Framework 
* [Thymeleaf](https://www.thymeleaf.org/) - Server-Side Java Template Engine
* [MySQL](https://www.mysql.com/) - Relational Database Management System
* [Maven](https://maven.apache.org/) - Project Management Tool
### Installation
 - Create MySQL User & Database
```sh
$ CREATE USER 'library'@'localhost' IDENTIFIED BY 'LNoa5eelBYdCJpW6';
$ CREATE DATABASE IF NOT EXISTS 'library';
$ GRANT ALL PRIVILEGES ON 'library'.* TO 'library'@'localhost';
$ FLUSH PRIVILEGES;
$ CREATE DATABASE library CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
Run the app.
#### Linux
```sh
$ cd library
$ ./mvnw spring:boot-run
```

#### Windows
```sh
$ cd library
$ mvnw spring:boot-run
```

License
----

MIT


**Free Software**