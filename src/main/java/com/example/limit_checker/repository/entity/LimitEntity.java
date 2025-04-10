package com.example.limit_checker.repository.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "limit", schema = "app")
public class LimitEntity {
    @Id
    @Column(name = "limit_id")
    @Setter(AccessLevel.NONE)
    private UUID limitId = UUID.randomUUID();

    private String account;

    @Column(name = "expense_category")
    private String expenseCategory;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    private BigDecimal sum;

    @Setter(AccessLevel.NONE)
    private OffsetDateTime datetime;

    @Column(name = "current_spent")

    private BigDecimal currentSpent;

    @Column(name = "spent_reset_datetime")
    private OffsetDateTime spentResetDatetime;

    @Builder
    public LimitEntity(String account, String expenseCategory,
                       String currencyShortname, BigDecimal sum,
                       BigDecimal currentSpent, OffsetDateTime spentResetDatetime) {
        this.account = account;
        this.expenseCategory = expenseCategory;
        this.currencyShortname = currencyShortname;
        this.sum = sum;
        this.currentSpent = currentSpent;
        this.spentResetDatetime = spentResetDatetime;
        this.datetime = OffsetDateTime.now();
    }





}
