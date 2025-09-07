package com.khoipd8.accounts.controller;

import com.khoipd8.accounts.constants.AccountsConstants;
import com.khoipd8.accounts.dto.CustomerDto;
import com.khoipd8.accounts.dto.ResponseDto;
import com.khoipd8.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts", description = "Operations for bank accounts")
@RestController
@RequestMapping(path = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class AccountsController {

    private final IAccountsService accountsService;

    @Operation(
            summary = "Create a new account",
            description = "Create an account for a customer using basic info (name, email, mobileNumber).",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = ResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "409", description = "Conflict (mobile already registered)"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) // ❌ bỏ dấu "/" thừa
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch customer & accounts by mobile number",
            description = "Return customer info and all accounts associated with the given mobile number.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = CustomerDto.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @GetMapping
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam("mobileNumber") @NotBlank String mobileNumber) {
        CustomerDto customerDto = accountsService.fetchAccountDetails(mobileNumber);
        return ResponseEntity.ok(customerDto);
    }
}
