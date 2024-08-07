package com.spring.library_management_system.service;

import com.spring.library_management_system.repository.BookRepository;
import com.spring.library_management_system.model.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Book> getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));;
        return ResponseEntity.ok(book);
    }

    @Transactional
    public ResponseEntity<Book> createBook(Book book) {
        Book createdBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @Transactional
    public ResponseEntity<Book> updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setIsbn(bookDetails.getIsbn());
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @Transactional
    public ResponseEntity<Void> deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
        bookRepository.delete(book);
        return ResponseEntity.ok().build();
    }
}
