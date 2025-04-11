package com.example.limit_checker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionInfoDto {
    @JsonProperty(value = "account_from", index = 0)
    private String accountFrom;

    @JsonProperty(value = "account_to", index = 1)
    private String accountTo;

    @JsonProperty(value = "currency_shortname", index = 2)
    private String currencyShortname;

    @JsonProperty(index = 3)
    private BigDecimal sum;

    @JsonProperty(value = "expense_category", index = 4)
    private String expenseCategory;

    @JsonProperty(index = 5)
    private OffsetDateTime datetime;

    @JsonProperty(value = "limit_sum", index = 6)
    private BigDecimal limitSum;

    @JsonProperty(value = "limit_datetime", index = 7)
    private OffsetDateTime limitDatetime;

    @JsonProperty(value = "limit_currency_shortname", index = 8)
    private String limitCurrencyShortname;
}
