package com.salon.Shop.resource;
import com.salon.Shop.entity.customer;
import com.salon.Shop.service.customerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("")
public class customerResource {
    private final customerService sampleSer;

    public customerResource(customerService sampleSer) {
        this.sampleSer = sampleSer;
    }

    @GetMapping("/all")
    public ResponseEntity<List<customer>> getAllcustomers(){
        List<customer> sample = sampleSer.findAllUsers();
        return new ResponseEntity<>(sample, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<customer> getcustomer(@PathVariable("id") Long id){
        customer sample = sampleSer.findUserById(id);
        return new ResponseEntity<>(sample, HttpStatus.OK);
    }
    @GetMapping("/find/email/{email}")
    public ResponseEntity<customer> getUserByEmail(@PathVariable("email") String email){
        customer user = sampleSer.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<customer> addcustomer(@RequestBody customer sample){
        customer newSample = sampleSer.addUser(sample);
        return new ResponseEntity<>(newSample, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<customer> updatcustomer(@RequestBody customer sample){
        customer updateSample = sampleSer.updateUser(sample);
        return new ResponseEntity<>(updateSample, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletecustomer(@PathVariable("id") Long id) {
        sampleSer.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
