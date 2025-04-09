package com.example.limit_checker.repository.projection;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface TransactionInfoProjection {
    String getAccountFrom();

    String getAccountTo();

    String getCurrencyShortname();

    BigDecimal getSum();

    String getExpenseCategory();

    OffsetDateTime getDatetime();

    BigDecimal getLimitSum();

    OffsetDateTime getLimitDatetime();

    String getLimitCurrencyShortname();

}
