package com.spring.library_management_system.service;

import com.spring.library_management_system.model.User;
import com.spring.library_management_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUsers {


    @Autowired
    private UserRepo userRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    public ResponseEntity<User> saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User saveUser = userRepo.save(user);
        return ResponseEntity.status(201).body(saveUser);
    }
}
