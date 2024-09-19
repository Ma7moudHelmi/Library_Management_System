package com.spring.library_management_system.controllers;

import com.spring.library_management_system.model.Patron;
import com.spring.library_management_system.service.PatronService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {


    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patron> getPatronById(@PathVariable @Min(1) Long id){
        return patronService.getPatronById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patron> createPatron(@Valid @RequestBody Patron patron){
        return patronService.createPatron(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable @Min(1) Long id,@Valid @RequestBody Patron patron){
        return patronService.updatePatron(id,patron);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id){
        return patronService.deletePatron(id);
    }

}
