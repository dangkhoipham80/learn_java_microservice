package com.khoipd8.accounts.service.impl;

import com.khoipd8.accounts.constants.AccountsConstants;
import com.khoipd8.accounts.dto.AccountsDto;
import com.khoipd8.accounts.dto.CustomerDto;
import com.khoipd8.accounts.entity.Accounts;
import com.khoipd8.accounts.entity.Customer;
import com.khoipd8.accounts.exception.CustomerAlreadyExistsException;
import com.khoipd8.accounts.exception.ResourceNotFoundException;
import com.khoipd8.accounts.mapper.AccountsMapper;
import com.khoipd8.accounts.mapper.CustomerMapper;
import com.khoipd8.accounts.repository.AccountsRepository;
import com.khoipd8.accounts.repository.CustomerRepository;
import com.khoipd8.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * Create a new account.
     *
     * @param customerDto the customer data for the new account
     */
    @Override
    @Transactional
    public void createAccount(CustomerDto customerDto) {
        customerRepository.findByMobileNumber(customerDto.getMobileNumber())
                .ifPresent(c -> {
                    throw new CustomerAlreadyExistsException(
                            "Customer already registered with given mobileNumber: " + customerDto.getMobileNumber()
                    );
                });

        // Map DTO -> Entity
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        // (Nếu chưa bật auditing thì tạm set)
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");

        // Lưu Customer trước
        Customer savedCustomer = customerRepository.save(customer);

        // Tạo Account gắn với Customer (KHÔNG tự set accountNumber vì dùng @GeneratedValue)
        Accounts account = new Accounts();
        account.setCustomer(savedCustomer);                // <- quan trọng: gán entity Customer
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");

        accountsRepository.save(account);
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        List<Accounts> accounts = accountsRepository.findAllByCustomer_CustomerId(customer.getCustomerId());

        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString());
        }

        // Map Customer -> DTO
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());

        // Map List<Accounts> -> List<AccountsDto>
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts));

        return customerDto;
    }
}
