package com.khoipd8.accounts.repository;

import com.khoipd8.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Finds a customer by the mobile number.
     *
     * @param mobileNumber the mobile number
     * @return the customer
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
