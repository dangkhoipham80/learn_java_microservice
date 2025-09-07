package com.khoipd8.accounts.mapper;

import com.khoipd8.accounts.dto.AccountsDto;
import com.khoipd8.accounts.entity.Accounts;

import java.util.List;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

    public static List<AccountsDto> mapToAccountsDto(List<Accounts> accountsList) {
        return accountsList.stream()
                .map(account -> mapToAccountsDto(account, new AccountsDto()))
                .toList();
    }
}
