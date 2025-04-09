package com.example.limit_checker.controller;

import com.example.limit_checker.dto.LimitCreateDto;
import com.example.limit_checker.dto.LimitInfoDto;
import com.example.limit_checker.dto.TransactionInfoDto;
import com.example.limit_checker.service.api.ILimitService;
import com.example.limit_checker.service.api.ITransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final ITransactionService transactionService;
    private final ILimitService limitService;

    public UserController(ITransactionService transactionService, ILimitService limitService) {
        this.transactionService = transactionService;
        this.limitService = limitService;
    }

    @GetMapping("/{account}/limits")
    public List<LimitInfoDto> getLimits(@PathVariable("account") String account,
                                        @RequestParam(value = "expenseCategory ", required = false)
                                        String expenseCategory) {
        if (expenseCategory != null && !expenseCategory.isBlank()) {
            return limitService.getAllByExpenseCategory(account, expenseCategory);
        } else {
            return limitService.getAll(account);
        }
    }

    @PostMapping("/{account}/limits")
    @ResponseStatus(HttpStatus.CREATED)
    public void createLimit(@PathVariable("account") String account,
                            @RequestBody @Valid LimitCreateDto limitCreateDto) {
    }

    @GetMapping("/{account}/transactions/exceeded-limit")
    public List<TransactionInfoDto> get(@PathVariable("account") String account,
                                        @RequestParam(value = "expenseCategory ", required = false)
                                        String expenseCategory) {
        if (expenseCategory != null && !expenseCategory.isBlank()) {
            return transactionService.getAllExceededLimitByExpenseCategory(account, expenseCategory);
        } else {
            return transactionService.getAllExceededLimit(account);
        }
    }


}
