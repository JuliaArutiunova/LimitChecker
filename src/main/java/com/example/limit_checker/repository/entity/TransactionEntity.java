package com.example.limit_checker.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "transaction", schema = "app")
public class TransactionEntity {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long transactionId;

    @Column(name = "account_from")
    private String accountFrom;

    @Column(name = "account_to")
    private String accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    private BigDecimal sum;

    @Column(name = "expense_category")
    private String expenseCategory;

    private OffsetDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "limit_id", foreignKey = @ForeignKey(name = "limit_FK"))
    private LimitEntity limit;

    @Column(name = "limit_exceeded")
    private boolean limitExceeded;

    @Builder
    public TransactionEntity(String accountFrom, String accountTo,
                             String currencyShortname, BigDecimal sum,
                             String expenseCategory, OffsetDateTime datetime,
                             LimitEntity limit, boolean limitExceeded) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currencyShortname = currencyShortname;
        this.sum = sum;
        this.expenseCategory = expenseCategory;
        this.datetime = datetime;
        this.limit = limit;
        this.limitExceeded = limitExceeded;
    }
}
