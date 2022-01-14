package com.salon.Shop.security;

import com.salon.Shop.entity.customer;
import com.salon.Shop.exception.NotFoundException;
import com.salon.Shop.repo.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private customerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        customer u = repo.findCustomerByEmail(email);
        if (u ==null) new NotFoundException("User by email " + email + " was not found");
        return new CustomUserDetails(u);
    }
}
