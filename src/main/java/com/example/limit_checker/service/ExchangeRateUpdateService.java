package com.example.limit_checker.service;

import com.example.limit_checker.dto.exchange.ExchangeRateDto;
import com.example.limit_checker.service.api.IExchangeRateService;
import com.example.limit_checker.service.feign.TwelveDataClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExchangeRateUpdateService {
    private final IExchangeRateService exchangeRateService;
    private final TwelveDataClient twelveDataClient;
    @Value("${client.twelve-data-api.interval}")
    private String interval;
    @Value("${client.twelve-data-api.api-key}")
    private String apiKey;
    @Value("#{'${exchange.currency-pairs}'.split(',')}")
    private List<String> currencyPairs;

    public ExchangeRateUpdateService(IExchangeRateService exchangeRateService, TwelveDataClient twelveDataClient) {
        this.exchangeRateService = exchangeRateService;
        this.twelveDataClient = twelveDataClient;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    private void updateExchangeRate() {

        try {
            for (String symbol : currencyPairs) {
                ExchangeRateDto exchangeRateDto =
                        twelveDataClient.getExchangeRateByEndDate(
                                LocalDate.now().plusDays(1).toString(),
                                1, symbol, interval, apiKey
                        );
                if (exchangeRateDto.getStatus().equals("error")) { //TODO exception
                    throw new RuntimeException("status - error");
                }
                exchangeRateService.update(
                        symbol.substring(0, 3),
                        symbol.substring(4),
                        new BigDecimal(exchangeRateDto.getValues().getFirst().getClose()),
                        LocalDate.parse(exchangeRateDto.getValues().getFirst().getDatetime())
                );
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());  //TODO !!!
        }

    }
}
