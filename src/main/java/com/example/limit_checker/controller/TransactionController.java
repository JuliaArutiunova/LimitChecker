package com.example.limit_checker.controller;

import com.example.limit_checker.dto.TransactionCreateDto;
import com.example.limit_checker.dto.error.ErrorResponseDto;
import com.example.limit_checker.service.api.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Tag(name = "Transaction API",
        description = "API for receiving and processing transactions from banking services")
public class TransactionController {
    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Receive Transaction",
            description = "Endpoint for receiving and processing new transactions from banking services.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = TransactionCreateDto.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Transaction successfully received and processed."),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid transaction data provided.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    public void save(@RequestBody @Valid TransactionCreateDto transactionCreateDto) {
        transactionService.create(transactionCreateDto);
    }
}
