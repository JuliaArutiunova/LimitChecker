package com.example.limit_checker.repository.projection;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface LimitInfoProjection {

    String getAccount();

    String getExpenseCategory();

    BigDecimal getSum();

    String getCurrencyShortname();

    OffsetDateTime getDatetime();

}
