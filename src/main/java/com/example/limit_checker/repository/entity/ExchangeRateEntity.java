package com.example.limit_checker.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "exchange_rate", schema = "app")
public class ExchangeRateEntity {
    @Id
    @Column(name = "exchange_rate_id")
    @Setter(AccessLevel.NONE)
    private UUID exchangeRateId = UUID.randomUUID();

    @Column(name = "currency_base")
    private String currencyBase;

    @Column(name = "currency_quote")
    private String currencyQuote;

    private BigDecimal rate;

    @Column(name = "exchange_date")
    private LocalDate exchangeDate;

    @Column(name = "dt_create")
    @Setter(AccessLevel.NONE)
    private OffsetDateTime dtCreate;

    @Column(name = "dt_update")
    private OffsetDateTime dtUpdate;

    @Builder
    public ExchangeRateEntity(String currencyBase, String currencyQuote, BigDecimal rate, LocalDate exchangeDate) {
        this.currencyBase = currencyBase;
        this.currencyQuote = currencyQuote;
        this.rate = rate;
        this.exchangeDate = exchangeDate;
    }

    @PrePersist
    void prePersist() {
        this.dtCreate = OffsetDateTime.now();
        this.dtUpdate = dtCreate;
    }
}
