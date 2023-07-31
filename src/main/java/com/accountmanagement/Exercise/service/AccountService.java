package com.accountmanagement.Exercise.service;

import com.accountmanagement.Exercise.dto.AccountCreationDTO;
import com.accountmanagement.Exercise.mapper.AccountMapper;
import com.accountmanagement.Exercise.model.Account;
import com.accountmanagement.Exercise.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    //save account, return a dto
    public AccountCreationDTO saveAccount(AccountCreationDTO accountCreationDTO) {
        Account account = accountMapper.dtoToAccount(accountCreationDTO);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.accountToDto(savedAccount);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        // Here you can add any business logic you need before fetching
        return accountRepository.findById(id).orElse(null);
    }

    public void deleteAccount(Long id) {
        // Here you can add any business logic you need before deleting
        accountRepository.deleteById(id);
    }

    public Account updateAccount(Account account) {
        // Here you can add any business logic you need before updating
        return accountRepository.save(account);
    }
}
