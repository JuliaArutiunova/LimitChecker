package com.example.limit_checker.service;

import com.example.limit_checker.dto.TransactionCreateDto;
import com.example.limit_checker.dto.TransactionInfoDto;
import com.example.limit_checker.repository.api.ITransactionRepository;
import com.example.limit_checker.repository.projection.TransactionInfoProjection;
import com.example.limit_checker.service.api.ITransactionService;

import com.example.limit_checker.service.mapper.TransactionInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final TransactionInfoMapper transactionInfoMapper;


    public TransactionService(ITransactionRepository transactionRepository,
                              TransactionInfoMapper transactionInfoMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionInfoMapper = transactionInfoMapper;
    }

    @Override
    public void create(TransactionCreateDto transactionCreateDto) {

    }

    @Override
    public List<TransactionInfoDto> getAllExceededLimit(String account) {

        List<TransactionInfoProjection> projections = transactionRepository
                .findExceededLimitByAccountOrderByDateDesc(account);

        return transactionInfoMapper.toTransactionInfoDtoList(projections);

    }
    @Override
    public List<TransactionInfoDto> getAllExceededLimitByExpenseCategory(String account, String expenseCategory){
        List<TransactionInfoProjection> projections = transactionRepository
                .findExceededLimitByAccountAndCategoryOrderByDateDesc(account, expenseCategory);
        return transactionInfoMapper.toTransactionInfoDtoList(projections);
    }


}
