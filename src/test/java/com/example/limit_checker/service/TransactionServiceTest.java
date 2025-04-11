package com.example.limit_checker.service;

import com.example.limit_checker.dto.TransactionCreateDto;
import com.example.limit_checker.repository.api.ITransactionRepository;
import com.example.limit_checker.repository.entity.LimitEntity;
import com.example.limit_checker.repository.entity.TransactionEntity;
import com.example.limit_checker.service.api.ILimitService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private ITransactionRepository transactionRepository;
    @Mock
    private ILimitService limitService;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    @Transactional
    void create_transactionWithinLimit_limitNotExceeded() {
        TransactionCreateDto createDto = new TransactionCreateDto();
        createDto.setAccountFrom("0000000000");
        createDto.setExpenseCategory("product");
        createDto.setCurrencyShortname("USD");
        createDto.setSum(BigDecimal.valueOf(100.00));
        createDto.setDatetime(OffsetDateTime.now());

        when(limitService.defineLimit("0000000000", "product")).thenReturn(
                LimitEntity.builder()
                        .sum(BigDecimal.valueOf(1000.00))
                        .currentSpent(BigDecimal.ZERO)
                        .currencyShortname("USD")
                        .spentResetDatetime(OffsetDateTime.now())
                        .build()
        );
        when(limitService.getDefaultCurrency()).thenReturn("USD");

        ArgumentCaptor<TransactionEntity> transactionCaptor = ArgumentCaptor.forClass(TransactionEntity.class);
        ArgumentCaptor<LimitEntity> limitCaptor = ArgumentCaptor.forClass(LimitEntity.class);

        transactionService.create(createDto);

        verify(transactionRepository, times(1)).saveAndFlush(transactionCaptor.capture());
        TransactionEntity savedTransaction = transactionCaptor.getValue();
        assertFalse(savedTransaction.isLimitExceeded());

        verify(limitService, times(1)).save(limitCaptor.capture());
        LimitEntity savedLimit = limitCaptor.getValue();
        assertEquals(BigDecimal.valueOf(100.00), savedLimit.getCurrentSpent());
    }

    @Test
    void createTransaction_existingLimit_exceeded() {

        TransactionCreateDto createDto = new TransactionCreateDto();
        createDto.setAccountFrom("0000000000");
        createDto.setExpenseCategory("product");
        createDto.setCurrencyShortname("USD");
        createDto.setSum(BigDecimal.valueOf(150.00));
        createDto.setDatetime(OffsetDateTime.now());

        LimitEntity existingLimit = LimitEntity.builder()
                .sum(BigDecimal.valueOf(200.00))
                .currentSpent(BigDecimal.valueOf(100.00))
                .currencyShortname("USD")
                .spentResetDatetime(OffsetDateTime.now())
                .build();

        when(limitService.defineLimit("0000000000", "product")).thenReturn(existingLimit);
        when(limitService.getDefaultCurrency()).thenReturn("USD");

        ArgumentCaptor<TransactionEntity> transactionCaptor = ArgumentCaptor.forClass(TransactionEntity.class);
        ArgumentCaptor<LimitEntity> limitCaptor = ArgumentCaptor.forClass(LimitEntity.class);

        transactionService.create(createDto);

        verify(transactionRepository, times(1)).saveAndFlush(transactionCaptor.capture());
        TransactionEntity savedTransaction = transactionCaptor.getValue();
        assertTrue(savedTransaction.isLimitExceeded());

        verify(limitService, times(1)).save(limitCaptor.capture());
        LimitEntity updatedLimit = limitCaptor.getValue();
        assertEquals(BigDecimal.valueOf(250.00), updatedLimit.getCurrentSpent());
    }

}
