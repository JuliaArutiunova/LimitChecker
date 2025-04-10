package com.example.limit_checker.repository.api;

import com.example.limit_checker.repository.entity.LimitEntity;
import com.example.limit_checker.repository.projection.LimitInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ILimitRepository extends JpaRepository<LimitEntity, UUID> {
    @Query("""
            SELECT
                l.account AS account,
                l.expenseCategory AS expenseCategory,
                l.sum AS sum,
                l.currencyShortname AS currencyShortname,
                l.datetime AS datetime
            FROM
                LimitEntity l
            WHERE
                l.account = :account ORDER BY l.datetime DESC""")
    List<LimitInfoProjection> findAllByAccountOrderByDateDesc(@Param("account") String account);

    @Query("""
            SELECT
                l.account AS account,
                l.expenseCategory AS expenseCategory,
                l.sum AS sum,
                l.currencyShortname AS currencyShortname,
                l.datetime AS datetime
            FROM
                LimitEntity l
            WHERE
                l.account = :account AND
                l.expenseCategory = :expenseCategory ORDER BY l.datetime DESC""")
    List<LimitInfoProjection> findAllByAccountAndCategoryOrderByDateDesc(
            @Param("account") String account,
            @Param("expenseCategory") String expenseCategory);

    @Query("""
            SELECT l
            FROM LimitEntity l
            WHERE l.datetime = (
                SELECT MAX(l.datetime)
                FROM LimitEntity l
                WHERE
                    l.account = :account AND
                    l.expenseCategory = :expenseCategory)""")
    LimitEntity findCurrentLimitByAccountAndCategory(@Param("account") String account,
                                                     @Param("expenseCategory") String expenseCategory);

    boolean existsByAccountAndExpenseCategory(String account, String expenseCategory);
}
