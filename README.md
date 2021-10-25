## Features
- Login / Register
- Change Password / Email
- Display Book, Author & Publisher
- Display Author's Books & Publisher's Published Books
- Add Book, Author & Publisher
- Delete Book, Author & Publisher _(admin only)_
- Change User Role (User | Admin) _(admin only)_

### Docker
```sh
$ mvn clean install -DskipTests
$ docker-compose up
````

##### Pre-defined Users
```sh
ROLE  | login - pass
-------------------
ADMIN | admin - 123
USER  | user  - 123
```