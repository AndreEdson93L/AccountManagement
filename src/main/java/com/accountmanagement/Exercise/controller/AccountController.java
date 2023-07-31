package com.accountmanagement.Exercise.controller;

import com.accountmanagement.Exercise.dto.AccountCreationDTO;
import com.accountmanagement.Exercise.model.Account;
import com.accountmanagement.Exercise.service.AccountService;
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

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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
    public ResponseEntity<AccountCreationDTO> createAccount(@RequestBody AccountCreationDTO accountCreationDTO) {
        return new ResponseEntity<>(accountService.saveAccount(accountCreationDTO), HttpStatus.OK);
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
