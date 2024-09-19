package com.spring.library_management_system.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class userPrincipal implements UserDetails {

    Patron patron ;

    public userPrincipal(Patron patron){
        this.patron = patron;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+patron.getRoles()));
    }

    @Override
    public String getPassword() {
        return patron.getPassword();
    }

    @Override
    public String getUsername() {
        return patron.getUsername();
    }


}
