package com.salon.Shop.repo;

import com.salon.Shop.entity.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface customerRepo extends JpaRepository<customer, Long> {
    Optional<customer> findCustomerById(Long id);

    customer findCustomerByEmail(String email);

    @Transactional
    void deleteCustomerById(Long id);
}