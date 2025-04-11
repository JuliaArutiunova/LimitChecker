package com.example.limit_checker.dto;

import com.example.limit_checker.validation.AccountId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LimitCreateDto {
    @JsonProperty("expense_category")
    @NotBlank(message = "a value is required")
    private String expenseCategory;

    @NotNull(message = "a value is required")
    private BigDecimal sum;

}
