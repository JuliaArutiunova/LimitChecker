package com.example.limit_checker.controller;

import com.example.limit_checker.dto.TransactionCreateDto;
import com.example.limit_checker.service.api.ITransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid TransactionCreateDto transactionCreateDto) {
        transactionService.create(transactionCreateDto);
    }
}
