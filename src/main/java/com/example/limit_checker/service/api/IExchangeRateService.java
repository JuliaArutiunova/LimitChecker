package com.example.limit_checker.service.api;

import com.example.limit_checker.dto.exchange.ExchangeRateDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IExchangeRateService {
    void update(String currencyBase, String currencyQuote, BigDecimal newRate, LocalDate exchangeDate);
    BigDecimal getCurrentRate(String currencyBase,String currencyQuote);
}
