# RestFulResources API

This project is a  RESTful API for managing user posts. It is built with Java and leverages JPA for database interactions.


## Usage

The API provides endpoints to manage users and their posts. Below are examples of the entities used in this project.

### Entities

#### User

The user entity represents a user in the system.

**Fields:**

- `id` (Integer): The unique identifier for the user.
- `name` (String): The name of the user. Must have at least 2 characters.
- `birthDate` (LocalDate): The birth date of the user. Must be in the past.
- `posts` (List<Post>): The list of posts made by the user. This field is ignored in the response.

#### Post

The Post entity represents a post made by a user.

**Fields:**

- `postId` (Integer): The unique identifier for the post.
- `description` (String): The description of the post. Must have at least 10 characters.
- `user` (User): The user who made the post.

### Endpoints

The API provides the following endpoints for managing users and posts:

#### Retrieve All Users

- **URL**: `/users`
- **Method**: `GET`
- **Description**: Retrieve a list of all users.

#### Retrieve User By ID

- **URL**: `/users/{id}`
- **Method**: `GET`
- **Description**: Retrieve a specific user by ID using HATEOAS to provide links to other resources.

#### Create User

- **URL**: `/users`
- **Method**: `POST`
- **Description**: Create a new user and return the URI of the created resource.

#### Delete User

- **URL**: `/users/{id}`
- **Method**: `DELETE`
- **Description**: Delete a user by ID.

### Services

#### UserDaoService

The UserDaoService class is responsible for managing user data. It provides methods to save, find, and delete users.

**Methods:**

- `save(user user)`: Saves a new user to the list and returns the saved user.
- `findAll()`: Returns a list of all users.
- `findOne(int id)`: Finds and returns a user by their ID.
- `deleteById(int id)`: Deletes a user by their ID.

