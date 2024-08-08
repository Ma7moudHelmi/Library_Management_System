## Library Management System

This project is a Spring Boot application that provides a library management system with RESTful API endpoints for managing books, patrons, and borrowing records.

### Table of Contents

- [Requirements](#requirements)
- [Setup and Running](#setup-and-running)
- [API Endpoints](#api-endpoints)
    - [Book Management](#book-management)
    - [Patron Management](#patron-management)
    - [Borrowing](#borrowing)
- [Testing](#testing)

### Requirements

- Java 21
- Maven 3.6+
- An IDE like IntelliJ IDEA or Eclipse

### Setup and Running

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Ma7moudHelmi/Library_Management_System.git
   cd Library-Management-System
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

4. The application will start and be accessible at `http://localhost:8080`.

### API Endpoints

#### Book Management

- **GET /api/books**: Retrieve a list of all books.
    - Example: `http://localhost:8080/api/books`

- **GET /api/books/{id}**: Retrieve details of a specific book by ID.
    - Example: `http://localhost:8080/api/books/1`

- **POST /api/books**: Add a new book to the library.
    - Example:
      ```
      POST http://localhost:8080/api/books
      Content-Type: application/json
      
      {
        "title": "New Book",
        "author": "Author Name",
        "publicationYear": 2024,
        "isbn": "1234567890123"
      }
      ```

- **PUT /api/books/{id}**: Update an existing book's information.
    - Example:
      ```
      PUT http://localhost:8080/api/books/1
      Content-Type: application/json
      
      {
        "title": "Updated Book",
        "author": "Author Name",
        "publicationYear": 2024,
        "isbn": "1234567890123"
      }
      ```

- **DELETE /api/books/{id}**: Remove a book from the library.
    - Example: `http://localhost:8080/api/books/1`

#### Patron Management

- **GET /api/patrons**: Retrieve a list of all patrons.
    - Example: `http://localhost:8080/api/patrons`

- **GET /api/patrons/{id}**: Retrieve details of a specific patron by ID.
    - Example: `http://localhost:8080/api/patrons/1`

- **POST /api/patrons**: Add a new patron to the system.
    - Example:
      ```
      POST http://localhost:8080/api/patrons
      Content-Type: application/json
      
      {
        "name": "New Patron",
        "contactInfo": "patron@example.com"
      }
      ```

- **PUT /api/patrons/{id}**: Update an existing patron's information.
    - Example:
      ```
      PUT http://localhost:8080/api/patrons/1
      Content-Type: application/json
      
      {
        "name": "Updated Patron",
        "contactInfo": "patron@example.com"
      }
      ```

- **DELETE /api/patrons/{id}**: Remove a patron from the system.
    - Example: `http://localhost:8080/api/patrons/1`

#### Borrowing

- **POST /api/borrow/{bookId}/patron/{patronId}**: Allow a patron to borrow a book.
    - Example: `http://localhost:8080/api/borrow/1/patron/1`

- **PUT /api/return/{bookId}/patron/{patronId}**: Record the return of a borrowed book by a patron.
    - Example: `http://localhost:8080/api/return/1/patron/1`

    
### Testing

Unit tests are written using SpringBootTest and Mockito. To run the tests:

```bash
mvn test
```

This command will run all the unit tests and generate a report in the `target/surefire-reports` directory.

---
