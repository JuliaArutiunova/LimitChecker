package com.example.limit_checker.controller;

import com.example.limit_checker.dto.TransactionCreateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid TransactionCreateDto transactionCreateDto) {
    }
}
