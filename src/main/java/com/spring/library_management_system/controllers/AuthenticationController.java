package com.spring.library_management_system.controllers;

import com.spring.library_management_system.model.Patron;
import com.spring.library_management_system.service.AuthenticationUsers;
import com.spring.library_management_system.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationUsers authenticationUsers;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Patron> register(@RequestBody Patron patron) {
        return authenticationUsers.saveUser(patron);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Patron patron) {
        try {

           Authentication authentication= authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(patron.getUsername(), patron.getPassword()));
            System.out.println(authentication.getAuthorities());
            return ResponseEntity.ok(jwtService.generateToken(patron.getUsername()));
        } catch (AuthenticationException ex) {
            throw new AuthenticationException("") {};
        }
    }
}
