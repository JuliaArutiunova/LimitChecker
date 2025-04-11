package com.example.limit_checker.exception;

import lombok.Getter;

@Getter
public class CurrencyApiRequestException extends RuntimeException{

    private final String message;
    private final String symbol;
    private final String endDate;

    public CurrencyApiRequestException(String message, String symbol, String endDate) {

        this.message = message;
        this.symbol = symbol;
        this.endDate = endDate;
    }

}
