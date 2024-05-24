package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.AccountRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.AccountResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.AccountService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Account", description = "Account API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "get an account by id")
    @GetMapping("/account/{id}")
    public ResponseEntity<ApiResponse<AccountResponseDTO>> getAccountById(@PathVariable("id") int id) {
        ApiResponse<AccountResponseDTO> response = accountService.getAccountById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new account")
    @PostMapping("/account")
    public ResponseEntity<ApiResponse<AccountResponseDTO>> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        var res = accountService.createAccount(accountRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing account")
    @PutMapping("/account/{id}")
    public ResponseEntity<ApiResponse<AccountResponseDTO>> updateAccount(@PathVariable int id, @RequestBody AccountRequestDTO accountRequestDTO) {
        var res = accountService.updateAccount(id, accountRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete an account")
    @DeleteMapping("/account/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable int id) {
        var res = accountService.deleteAccount(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
