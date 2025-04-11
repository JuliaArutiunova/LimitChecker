package com.example.limit_checker.service;

import com.example.limit_checker.repository.api.IExchangeRateRepository;
import com.example.limit_checker.repository.entity.ExchangeRateEntity;
import com.example.limit_checker.service.api.IExchangeRateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
@Service
public class ExchangeRateService implements IExchangeRateService {
    private final IExchangeRateRepository exchangeRateRepository;

    public ExchangeRateService(IExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    @Transactional
    public void update(String currencyBase, String currencyQuote, BigDecimal newRate, LocalDate exchangeDate) {
        ExchangeRateEntity exchangeRateEntity =
                exchangeRateRepository
                        .findByCurrencyBaseAndCurrencyQuote(currencyBase, currencyQuote)
                        .orElseThrow(() -> new RuntimeException("exchangeRate not found")); //TODO exception

        exchangeRateEntity.setRate(newRate);

        exchangeRateEntity.setDtUpdate(OffsetDateTime.now());
        exchangeRateEntity.setExchangeDate(exchangeDate);

        exchangeRateRepository.saveAndFlush(exchangeRateEntity);

    }

    @Override
    @Transactional
    public BigDecimal getCurrentRate(String currencyBase, String currencyQuote) {
        return exchangeRateRepository.getRate(currencyBase, currencyQuote);
    }
}
