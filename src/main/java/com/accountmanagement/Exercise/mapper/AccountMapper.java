package com.accountmanagement.Exercise.mapper;


import com.accountmanagement.Exercise.dto.AccountCreationDTO;
import com.accountmanagement.Exercise.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account dtoToAccount(AccountCreationDTO accountCreationDTO);
    AccountCreationDTO accountToDto(Account account);
}

