package com.example.limit_checker.service.api;

import com.example.limit_checker.dto.LimitCreateDto;
import com.example.limit_checker.dto.LimitInfoDto;
import com.example.limit_checker.repository.entity.LimitEntity;

import java.util.List;

public interface ILimitService {
    void create(String account, LimitCreateDto limitCreateDto);

    List<LimitInfoDto> getAll(String account);

    List<LimitInfoDto> getAllByExpenseCategory(String account, String expenseCategory);

    LimitEntity createDefault(String account, String expenseCategory);

    void save(LimitEntity limitEntity);

    LimitEntity defineLimit(String accountFrom, String expenseCategory);

    String getDefaultCurrency();
}
