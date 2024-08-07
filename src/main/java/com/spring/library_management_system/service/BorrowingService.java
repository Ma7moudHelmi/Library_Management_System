package com.spring.library_management_system.service;

import com.spring.library_management_system.model.Book;
import com.spring.library_management_system.model.BorrowingRecords;
import com.spring.library_management_system.model.Patron;
import com.spring.library_management_system.repository.BookRepository;
import com.spring.library_management_system.repository.BorrowingRepository;
import com.spring.library_management_system.repository.PatronRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Transactional
    public ResponseEntity<BorrowingRecords> borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + bookId + " not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new EntityNotFoundException("Patron with id " + patronId + " not found"));

        BorrowingRecords borrowingRecord = new BorrowingRecords();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        return ResponseEntity.ok(borrowingRepository.save(borrowingRecord));
    }

    @Transactional
    public ResponseEntity<BorrowingRecords> returnBook(Long bookId, Long patronId) {
        BorrowingRecords borrowingRecord = borrowingRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new EntityNotFoundException("Patron or book is not found"));

        borrowingRecord.setReturnDate(LocalDate.now());
        return ResponseEntity.ok(borrowingRepository.save(borrowingRecord));
    }
}
