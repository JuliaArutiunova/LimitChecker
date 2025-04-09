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
    @JsonProperty("account_from")
    private String accountFrom;

    @JsonProperty("account_to")
    private String accountTo;

    @JsonProperty("currency_shortname")
    private String currencyShortname;

    private BigDecimal sum;

    @JsonProperty("expense_category")
    private String expenseCategory;

    private OffsetDateTime datetime;

    @JsonProperty("limit_sum")
    private BigDecimal limitSum;

    @JsonProperty("limit_datetime")
    private OffsetDateTime limitDatetime;

    @JsonProperty("limit_currency_shortname")
    private String limitCurrencyShortname;
}
