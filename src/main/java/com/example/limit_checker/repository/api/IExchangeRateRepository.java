package com.example.limit_checker.repository.api;

import com.example.limit_checker.repository.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface IExchangeRateRepository extends JpaRepository<ExchangeRateEntity, UUID> {
    @Query("""
            SELECT e.rate
            FROM ExchangeRateEntity e
            WHERE
                e.currencyBase = :currencyBase AND
                e.currencyQuote = :currencyQuote""")
    BigDecimal getRate(@Param("currencyBase") String currencyBase,
                       @Param("currencyQuote") String currencyQuote);

    Optional<ExchangeRateEntity> findByCurrencyBaseAndCurrencyQuote(String currencyBase, String currencyQuote);
}
