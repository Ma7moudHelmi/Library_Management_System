package com.spring.library_management_system.service;

import com.spring.library_management_system.model.Patron;
import com.spring.library_management_system.model.userPrincipal;
import com.spring.library_management_system.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsServices  implements UserDetailsService {


    @Autowired
    private PatronRepository patronRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Patron userData = patronRepository.findUserByUsername(username);

        if(userData == null){
            throw new UsernameNotFoundException("Username is incorrect");
        }

        return new userPrincipal(userData);
    }
}
