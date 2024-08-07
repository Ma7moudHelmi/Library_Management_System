package com.spring.library_management_system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.library_management_system.controllers.BorrowingController;
import com.spring.library_management_system.model.BorrowingRecords;
import com.spring.library_management_system.model.Patron;
import com.spring.library_management_system.service.BorrowingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BorrowingController.class)
public class BorrowingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowingService borrowingService;

    private BorrowingRecords borrowingRecords;
    @Test
    public void shouldBorrowBook() throws Exception {
        given(borrowingService.borrowBook(anyLong(),anyLong())).willReturn(new ResponseEntity<>(borrowingRecords, HttpStatus.OK));


        mockMvc.perform(post("/api/borrow/{bookId}/patron/{patronId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBook() throws Exception {
        given(borrowingService.borrowBook(anyLong(),anyLong())).willReturn(new ResponseEntity<>(borrowingRecords, HttpStatus.OK));

        mockMvc.perform(put("/api/return/{bookId}/patron/{patronId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}