package com.accountmanagement.Exercise.service;

import com.accountmanagement.Exercise.dto.AccountCreationDTO;
import com.accountmanagement.Exercise.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountCreationDTO saveAccount(AccountCreationDTO accountDTO);
    List<Account> getAccounts();
    Optional<Account> getAccount(Long id);
    void deleteAccount(Long id);
    Account updateAccount(Account account);
}
