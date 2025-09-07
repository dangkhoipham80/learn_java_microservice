package com.khoipd8.accounts.service;

import com.khoipd8.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create a new account
     *
     * @param customerDto the customer data for the new account
     */
    void createAccount(CustomerDto customerDto);
}
