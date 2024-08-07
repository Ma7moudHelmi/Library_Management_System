package com.spring.library_management_system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.library_management_system.controllers.PatronController;
import com.spring.library_management_system.model.Patron;
import com.spring.library_management_system.service.PatronService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatronController.class)
public class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    private Patron patron;

    @BeforeEach
    public void setup() {
        patron = new Patron();
        patron.setId(1L);
        patron.setName("Test Patron");
        patron.setContactInfo("test@example.com");
    }

    @Test
    public void shouldReturnAllPatrons() throws Exception {
        given(patronService.getAllPatrons()).willReturn(Arrays.asList(patron));

        mockMvc.perform(get("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Patron"));
    }

    @Test
    public void shouldReturnPatronById() throws Exception {
        given(patronService.getPatronById(anyLong())).willReturn(new ResponseEntity<>(patron, HttpStatus.OK));

        mockMvc.perform(get("/api/patrons/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Patron"));
    }

    @Test
    public void shouldCreatePatron() throws Exception {
        given(patronService.createPatron(any(Patron.class))).willReturn(new ResponseEntity<>(patron, HttpStatus.CREATED));

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patron)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Patron"));
    }

    @Test
    public void shouldUpdatePatron() throws Exception {
        given(patronService.updatePatron(anyLong(), any(Patron.class))).willReturn(new ResponseEntity<>(patron, HttpStatus.OK));

        mockMvc.perform(put("/api/patrons/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patron)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Patron"));
    }

    @Test
    public void shouldDeletePatron() throws Exception {
        given(patronService.deletePatron(anyLong())).willReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(delete("/api/patrons/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
