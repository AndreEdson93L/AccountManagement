package com.accountmanagement.Exercise.service.implementations;

import com.accountmanagement.Exercise.dto.AccountCreationDTO;
import com.accountmanagement.Exercise.mapper.AccountMapper;
import com.accountmanagement.Exercise.model.Account;
import com.accountmanagement.Exercise.repository.AccountRepository;
import com.accountmanagement.Exercise.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountCreationDTO saveAccount(AccountCreationDTO accountCreationDTO) {
        Account account = accountMapper.dtoToAccount(accountCreationDTO);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.accountToDto(savedAccount);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account updateAccount(Long id, AccountCreationDTO accountDTO) {
        Optional<Account> existingAccountOpt = accountRepository.findById(id);
        if(existingAccountOpt.isPresent()){
            Account existingAccount = existingAccountOpt.get();

            //Update properties of the account
            existingAccount.setEmail(accountDTO.getEmail());
            existingAccount.setPassword(accountDTO.getPassword());
            existingAccount.setNickname(accountDTO.getNickname());
            existingAccount.setConsent(accountDTO.getConsent());
            existingAccount.setRole(accountDTO.getRole());

            //Save in the db
            return accountRepository.save(existingAccount);
        } else {
            throw new EntityNotFoundException("Account with id " + id + " not found");
        }
    }
}
