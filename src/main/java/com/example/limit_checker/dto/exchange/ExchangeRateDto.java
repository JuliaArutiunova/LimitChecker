package com.example.limit_checker.dto.exchange;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {

    private ExchangeRateMeta meta;
    private List<ExchangeRateValue> values;
    private String status;

}
