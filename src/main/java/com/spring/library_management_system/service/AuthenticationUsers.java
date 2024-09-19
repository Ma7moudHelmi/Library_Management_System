package com.spring.library_management_system.service;

import com.spring.library_management_system.model.Patron;
import com.spring.library_management_system.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUsers {


    @Autowired
    private PatronRepository patronRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    public ResponseEntity<Patron> saveUser(Patron patron){
        patron.setPassword(bCryptPasswordEncoder.encode(patron.getPassword()));
        patron.setRoles("USER");

        Patron saveUser = patronRepository.save(patron);

        return ResponseEntity.status(201).body(saveUser);
    }
}
