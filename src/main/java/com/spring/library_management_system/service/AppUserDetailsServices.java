package com.spring.library_management_system.service;

import com.spring.library_management_system.model.User;
import com.spring.library_management_system.model.userPrincipal;
import com.spring.library_management_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsServices  implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User userData = userRepo.findUserByUsername(username);

        if(userData == null){
            throw new UsernameNotFoundException("Username is incorrect");
        }

        return new userPrincipal(userData);
    }
}
