package com.example.limit_checker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitInfoDto {

    private String account;

    @JsonProperty("expense_category")
    private String expenseCategory;

    private BigDecimal sum;

    @JsonProperty("currency_shortname")
    private String currencyShortname;

    private OffsetDateTime datetime;




}
