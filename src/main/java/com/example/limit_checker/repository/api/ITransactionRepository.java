package com.example.limit_checker.repository.api;

import com.example.limit_checker.repository.entity.TransactionEntity;
import com.example.limit_checker.repository.projection.TransactionInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {
    @Query(""" 
            SELECT
                t.accountFrom AS accountFrom,
                t.accountTo AS accountTo,
                t.currencyShortname AS currencyShortname,
                t.sum AS sum,
                t.expenseCategory AS expenseCategory,
                t.datetime AS datetime,
                l.sum AS limitSum,
                l.datetime AS limitDatetime,
                l.currencyShortname AS limitCurrencyShortname
            FROM
                TransactionEntity t
            JOIN
                LimitEntity l ON t.limit.limitId = l.limitId
            WHERE
                t.accountFrom = :account
                AND t.limitExceeded = true ORDER BY t.datetime DESC""")
    List<TransactionInfoProjection> findExceededLimitByAccountOrderByDateDesc(@Param("account") String account);

    @Query(""" 
            SELECT
                t.accountFrom AS accountFrom,
                t.accountTo AS accountTo,
                t.currencyShortname AS currencyShortname,
                t.sum AS sum,
                t.expenseCategory AS expenseCategory,
                t.datetime AS datetime,
                l.sum AS limitSum,
                l.datetime AS limitDatetime,
                l.currencyShortname AS limitCurrencyShortname
            FROM
                TransactionEntity t
            JOIN
                LimitEntity l ON t.limit.limitId = l.limitId
            WHERE
                t.accountFrom = :account
                AND t.expenseCategory = :expenseCategory
                AND t.limitExceeded = true ORDER BY t.datetime DESC""")
    List<TransactionInfoProjection> findExceededLimitByAccountAndCategoryOrderByDateDesc(
            @Param("account") String account,
            @Param("expenseCategory") String expenseCategory);

}
