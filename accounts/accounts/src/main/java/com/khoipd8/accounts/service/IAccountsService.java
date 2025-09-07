package com.khoipd8.accounts.service;

import com.khoipd8.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create a new account
     *
     * @param customerDto the customer data for the new account
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetches the account details for a customer by mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return the customer details
     */
    CustomerDto fetchAccountDetails(String mobileNumber);
}
