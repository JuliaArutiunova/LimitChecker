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
    @JsonProperty(index = 0)
    private String account;

    @JsonProperty(value = "expense_category", index = 3)
    private String expenseCategory;

    @JsonProperty(index = 1)
    private BigDecimal sum;

    @JsonProperty(value = "currency_shortname", index = 2)
    private String currencyShortname;

    @JsonProperty(index = 4)
    private OffsetDateTime datetime;


}
