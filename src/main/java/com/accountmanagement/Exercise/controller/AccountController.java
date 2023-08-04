package com.accountmanagement.Exercise.controller;

import com.accountmanagement.Exercise.dto.AccountCreationDTO;
import com.accountmanagement.Exercise.mapper.AccountMapper;
import com.accountmanagement.Exercise.model.Account;
import com.accountmanagement.Exercise.service.implementations.AccountServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountServiceImpl accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountServiceImpl accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PostMapping("create")
    @Operation(summary = "Create a new account.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account created successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))}
            )
    })
    public ResponseEntity<AccountCreationDTO> createAccount(@Valid @RequestBody AccountCreationDTO accountCreationDTO) {
        return new ResponseEntity<>(accountService.saveAccount(accountCreationDTO), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get an account by ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the account",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AccountCreationDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account with the given ID not found",
                    content = @Content)
    })
    public ResponseEntity<AccountCreationDTO> getAccount(@PathVariable Long id) {
        return accountService.getAccount(id)
                .map(account -> new ResponseEntity<>(accountMapper.accountToDto(account), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete an account by ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account deleted successfully",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account with the given ID not found",
                    content = @Content)
    })
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        if (accountService.getAccount(id).isPresent()) {
            accountService.deleteAccount(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    @Operation(summary = "Update an account.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account updated successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AccountCreationDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account with the given ID not found",
                    content = @Content)
    })
    public ResponseEntity<AccountCreationDTO> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountCreationDTO accountDTO) {
        Optional<Account> existingAccount = accountService.getAccount(id);
        if (existingAccount.isPresent()) {
            Account updatedAccount = accountService.updateAccount(id, accountDTO);
            return new ResponseEntity<>(accountMapper.accountToDto(updatedAccount), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list")
    @Operation(summary = "List all accounts.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of accounts",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = List.class))}
            )
    })
    public ResponseEntity<List<Account>> listAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }

    // Add more endpoints as per your requirements.
}
