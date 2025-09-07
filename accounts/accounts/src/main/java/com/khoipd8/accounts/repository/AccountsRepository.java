package com.khoipd8.accounts.repository;

import com.khoipd8.accounts.entity.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    /**
     * Finds all accounts by a customer's ID.
     *
     * @param customerId the customer's ID
     * @return list of accounts
     */
    List<Accounts> findAllByCustomer_CustomerId(Long customerId);

    /**
     * Finds all accounts by a customer's ID.
     *
     * @param customerId the customer's ID
     * @param pageable   the pagination information
     * @return a page of accounts
     */
    Page<Accounts> findAllByCustomer_CustomerId(Long customerId, Pageable pageable);

}
