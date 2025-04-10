package com.example.limit_checker.dto.exchange;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateValue {
    private String datetime;
    private String open;
    private String high;
    private String low;
    private String close;
}
