package com.spring.library_management_system.service;

import com.spring.library_management_system.model.Patron;
import com.spring.library_management_system.repository.PatronRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Transactional(readOnly = true)
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Patron> getPatronById(Long id) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patron with id " + id + " not found"));
        return ResponseEntity.ok(patron);
    }

    @Transactional
    public ResponseEntity<Patron> createPatron(Patron patron) {
        Patron createdPatron = patronRepository.save(patron);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatron);
    }

    @Transactional
    public ResponseEntity<Patron> updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patron with id " + id + " not found"));

        patron.setName(patronDetails.getName());
        patron.setContactInfo(patronDetails.getContactInfo());
        return ResponseEntity.ok(patronRepository.save(patron));
    }

    @Transactional
    public ResponseEntity<Void> deletePatron(Long id) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patron with id " + id + " not found"));
        patronRepository.delete(patron);
        return ResponseEntity.ok().build();
    }
}

