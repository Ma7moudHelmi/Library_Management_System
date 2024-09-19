package com.spring.library_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is Required")
    private String name;

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;

    private String roles;

    @NotBlank(message = "Contact info is Required")
    @Email(message = "Contact info should be a valid email address")
    private String contactInfo;
}
