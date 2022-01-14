package com.salon.Shop.service;

import com.salon.Shop.entity.customer;
import com.salon.Shop.exception.NotFoundException;
import com.salon.Shop.repo.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class customerService {
    private final customerRepo userRep;

    @Autowired
    public customerService(customerRepo userRep) {
        this.userRep = userRep;
    }

    public customer addUser(customer user){
        user.setPassword(encrypt(user.getPassword()));
        return userRep.save(user);
    }

    public customer findUserByEmail(String email){
        customer u = userRep.findCustomerByEmail(email);
        if (u ==null) new NotFoundException("User by email " + email + " was not found");
        return u;
    }

    public List<customer> findAllUsers(){
        return userRep.findAll();
    }

    public  customer updateUser(customer user){
        return userRep.save(user);
    }

    public customer findUserById(Long id){
        return userRep.findCustomerById(id)
                .orElseThrow(() -> new NotFoundException("User by id " + id + " was not found"));
    }

    public  void deleteUser(Long id){
        userRep.deleteCustomerById(id);
    }

    String encrypt(String data){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(data);
    }

}
