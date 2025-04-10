package com.example.limit_checker.dto.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateMeta {
    private String symbol;
    private String interval;
    @JsonProperty("currency_base")
    private String currencyBase;
    @JsonProperty("currency_quote")
    private String currencyQuote;
    private String type;
}
