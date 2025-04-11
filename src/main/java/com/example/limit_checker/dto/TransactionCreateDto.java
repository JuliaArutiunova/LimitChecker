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
    @JsonProperty(value = "account_from")
    @NotBlank(message = "a value is required")
    @AccountId
    private String accountFrom;

    @JsonProperty(value = "account_to")
    @NotBlank(message = "a value is required")
    @AccountId
    private String accountTo;

    @JsonProperty(value = "currency_shortname")
    @NotBlank(message = "a value is required")
    private String currencyShortname;

    @NotNull(message = "a value is required")
    private BigDecimal sum;

    @JsonProperty(value = "expense_category")
    @NotBlank(message = "a value is required")
    private String expenseCategory;

    @NotNull(message = "a value is required")
    private OffsetDateTime datetime;

}
