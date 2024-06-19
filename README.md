# Social media backend

This project is a  RESTFUL API for managing  users and user posts. It is built with Java and leverages JPA for database interactions.

It also makes use of various spring dependancies that are all outlined in the pom.xml file

## Usage

The API provides endpoints to manage users and their posts. Below the entities used in this project.

## 1. User

The user entity represents a user in the system.

**Fields:**

- `id` (Integer): The unique identifier for the user.
- `name` (String): The name of the user. Must have at least 2 characters.
- `birthDate` (LocalDate): The birth date of the user. Must be in the past.
- `posts` (List<Post>): The list of posts made by the user. This field is ignored in the response.

## 2. Post

The Post entity represents a post made by a user.

**Fields:**

- `postId` (Integer): The unique identifier for the post.
- `description` (String): The description of the post. Must have at least 10 characters.
- `user` (User): The user who made the post.

## URLs

- Basic Resources
  - http://localhost:8080/users
  - http://localhost:8080/users/1
  
- JPA Resources
  - http://localhost:8080/jpa/users
  - http://localhost:8080/jpa/users/1
  - http://localhost:8080/jpa/users/10001/posts
  
- Swagger
  - http://localhost:8080/swagger-ui.html
  - http://localhost:8080/v3/api-docs
  
- H2-Console
  - http://localhost:8080/h2-console
  
- HAL Browser
  - http://localhost:8080

## Endpoints

The Swagger url above shows a clear description of all endpoint provided by the API


#### UserDaoService

The UserDaoService class is responsible for managing user data. It provides methods to save, find, and delete users.


#### PostRepository

The PostRepository interface extends JpaRepository and provides methods for CRUD operations on the Post entity.

#### UserRepository

The UserRepository interface extends JpaRepository and provides methods for CRUD operations on the User entity.


## Spring Application Configuration

### Database Configuration

The API includes integration with both SQL and H2 databases for data storage. To use either simply modify the application properties file as follows

#### H2 Database

For H2 in-memory database:

- **URL**: jdbc:h2:mem:testdb

- **Defer Data Source Initialization**: true


#### MySQL Database

- **URL**: jdbc:mysql://localhost:3306/social-media-database
- **Username**: social-media-user
- **Password**: dummypassword
- **Show SQL**: true

**Note: To use MySQL the database has to be first initialized as a docker container on your local machine.**
#### Hibernate Configuration

- **DDL Auto**: update
- **Hibernate Dialect**: org.hibernate.dialect.MySQLDialect

