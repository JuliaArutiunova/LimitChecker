package com.example.limit_checker.service.api;

import com.example.limit_checker.dto.TransactionCreateDto;
import com.example.limit_checker.dto.TransactionInfoDto;

import java.util.List;

public interface ITransactionService {
    void create(TransactionCreateDto transactionCreateDto);

    List<TransactionInfoDto> getAllExceededLimit(String account);

    List<TransactionInfoDto> getAllExceededLimitByExpenseCategory(String account, String expenseCategory);
}
