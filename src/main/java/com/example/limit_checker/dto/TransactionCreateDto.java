package com.example.limit_checker.dto;

import com.example.limit_checker.validation.AccountId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TransactionCreateDto {
    @JsonProperty("account_from")
    @NotBlank
    @AccountId
    private String accountFrom;

    @JsonProperty("account_to")
    @NotBlank
    @AccountId
    private String accountTo;

    @JsonProperty("currency_shortname")
    @NotBlank
    private String currencyShortname;

    @NotNull
    private BigDecimal sum;

    @JsonProperty("expense_category")
    @NotBlank
    private String expenseCategory;

    @NotNull
    private OffsetDateTime datetime;

}
