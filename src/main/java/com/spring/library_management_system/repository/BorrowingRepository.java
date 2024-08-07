package com.spring.library_management_system.repository;

import com.spring.library_management_system.model.BorrowingRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingRecords,Long> {
    @Query("SELECT borrow FROM BorrowingRecords borrow WHERE borrow.book.id = :bookId AND borrow.patron.id = :patronId AND borrow.returnDate IS NULL")
    Optional<BorrowingRecords> findByBookIdAndPatronIdAndReturnDateIsNull( Long bookId,Long patronId);
}
