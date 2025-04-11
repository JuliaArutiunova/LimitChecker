package com.example.limit_checker.service;

import com.example.limit_checker.dto.TransactionCreateDto;
import com.example.limit_checker.dto.TransactionInfoDto;
import com.example.limit_checker.repository.api.ITransactionRepository;
import com.example.limit_checker.repository.entity.LimitEntity;
import com.example.limit_checker.repository.entity.TransactionEntity;
import com.example.limit_checker.repository.projection.TransactionInfoProjection;
import com.example.limit_checker.service.api.IExchangeRateService;
import com.example.limit_checker.service.api.ILimitService;
import com.example.limit_checker.service.api.ITransactionService;

import com.example.limit_checker.service.mapper.TransactionInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final TransactionInfoMapper transactionInfoMapper;
    private final ILimitService limitService;
    private final IExchangeRateService exchangeRateService;


    public TransactionService(ITransactionRepository transactionRepository,
                              TransactionInfoMapper transactionInfoMapper, ILimitService limitService, IExchangeRateService exchangeRateService) {
        this.transactionRepository = transactionRepository;
        this.transactionInfoMapper = transactionInfoMapper;
        this.limitService = limitService;
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    @Transactional
    public void create(TransactionCreateDto transactionCreateDto) {
        String accountFrom = transactionCreateDto.getAccountFrom();
        String expenseCategory = transactionCreateDto.getExpenseCategory();
        String transactionCurrency = transactionCreateDto.getCurrencyShortname();

        LimitEntity limitEntity = limitService.defineLimit(accountFrom, expenseCategory);

        BigDecimal convertedSum;
        if (!transactionCreateDto.getCurrencyShortname().equals(limitService.getDefaultCurrency())) {
            convertedSum = convertSum(transactionCreateDto.getSum(),
                    transactionCurrency, limitEntity.getCurrencyShortname());
        } else {
            convertedSum = transactionCreateDto.getSum();
        }

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .accountFrom(accountFrom)
                .accountTo(transactionCreateDto.getAccountTo())
                .limit(limitEntity)
                .sum(transactionCreateDto.getSum())
                .currencyShortname(transactionCurrency)
                .expenseCategory(expenseCategory)
                .datetime(transactionCreateDto.getDatetime())
                .build();

        BigDecimal sumSpent = limitEntity.getCurrentSpent().add(convertedSum);

        if (limitEntity.getSum().compareTo(sumSpent) < 0) {
            transactionEntity.setLimitExceeded(true);
        }
        limitEntity.setCurrentSpent(sumSpent);

        limitService.save(limitEntity);
        transactionRepository.saveAndFlush(transactionEntity);

    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionInfoDto> getAllExceededLimit(String account) {

        List<TransactionInfoProjection> projections = transactionRepository
                .findExceededLimitByAccountOrderByDateDesc(account);

        return transactionInfoMapper.toTransactionInfoDtoList(projections);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionInfoDto> getAllExceededLimitByExpenseCategory(String account, String expenseCategory) {
        List<TransactionInfoProjection> projections = transactionRepository
                .findExceededLimitByAccountAndCategoryOrderByDateDesc(account, expenseCategory);
        return transactionInfoMapper.toTransactionInfoDtoList(projections);
    }

    private BigDecimal convertSum(BigDecimal sum, String currencyBase, String currencyQuote) {
        BigDecimal rate = exchangeRateService.getCurrentRate(currencyBase, currencyQuote);
        return sum.multiply(rate);
    }


}
