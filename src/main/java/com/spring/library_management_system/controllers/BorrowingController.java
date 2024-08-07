package com.spring.library_management_system.controllers;

import com.spring.library_management_system.model.BorrowingRecords;
import com.spring.library_management_system.service.BorrowingService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecords> borrowBook(@PathVariable @Min(1) Long bookId, @PathVariable @Min(1) Long patronId){
        return borrowingService.borrowBook(bookId,patronId);
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecords> returnBook(@PathVariable @Min(1) Long bookId, @PathVariable @Min(1) Long patronId){
        return borrowingService.returnBook(bookId,patronId);
    }
}
