package com.example.limit_checker.service.feign;

import com.example.limit_checker.dto.exchange.ExchangeRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "twelveData-api", url = "${client.twelve-data-api.url}")
public interface TwelveDataClient {
    @GetMapping
    ExchangeRateDto getExchangeRateByEndDate(@RequestParam(value = "end_date") String endDate,
                                             @RequestParam(value = "outputsize") int outputSize,
                                             @RequestParam String symbol,
                                             @RequestParam String interval,
                                             @RequestParam String apikey);

}
