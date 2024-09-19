package com.spring.library_management_system.repository;

import com.spring.library_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findUserByUsername(String username);
}
