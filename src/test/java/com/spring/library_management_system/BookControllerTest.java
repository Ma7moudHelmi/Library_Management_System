package com.spring.library_management_system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.library_management_system.controllers.BookController;
import com.spring.library_management_system.model.Book;
import com.spring.library_management_system.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book;

    @BeforeEach
    public void setup() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPublicationYear(2023);
        book.setIsbn("1234567890123");
    }

    @Test
    public void shouldReturnAllBooks() throws Exception {
        given(bookService.getAllBooks()).willReturn(Arrays.asList(book));

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }

    @Test
    public void shouldReturnBookById() throws Exception {
        given(bookService.getBookById(anyLong())).willReturn(new ResponseEntity<>(book, HttpStatus.OK));

        mockMvc.perform(get("/api/books/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    public void shouldCreateBook() throws Exception {
        given(bookService.createBook(any(Book.class))).willReturn(new ResponseEntity<>(book, HttpStatus.CREATED));

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        given(bookService.updateBook(anyLong(), any(Book.class))).willReturn(new ResponseEntity<>(book, HttpStatus.OK));

        mockMvc.perform(put("/api/books/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        given(bookService.deleteBook(anyLong())).willReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(delete("/api/books/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
