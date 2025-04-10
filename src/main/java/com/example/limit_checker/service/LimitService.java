package com.example.limit_checker.service;

import com.example.limit_checker.dto.LimitCreateDto;
import com.example.limit_checker.dto.LimitInfoDto;
import com.example.limit_checker.repository.api.ILimitRepository;
import com.example.limit_checker.repository.entity.LimitEntity;
import com.example.limit_checker.repository.projection.LimitInfoProjection;
import com.example.limit_checker.service.api.ILimitService;
import com.example.limit_checker.service.mapper.LimitInfoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class LimitService implements ILimitService {

    private final ILimitRepository limitRepository;
    private final LimitInfoMapper limitInfoMapper;
    @Value("${limit.default-currency}")
    public String defaultLimitCurrency;
    @Value("${limit.default-sum}")
    private BigDecimal defaultLimitSum;

    public LimitService(ILimitRepository limitRepository, LimitInfoMapper limitInfoMapper) {
        this.limitRepository = limitRepository;
        this.limitInfoMapper = limitInfoMapper;
    }

    @Override
    @Transactional
    public void create(String account, LimitCreateDto limitCreateDto) {
        LimitEntity currentLimit =
                limitRepository.findCurrentLimitByAccountAndCategory(account, limitCreateDto.getExpenseCategory());

        LimitEntity newLimit = LimitEntity.builder()
                .account(account)
                .expenseCategory(limitCreateDto.getExpenseCategory())
                .sum(limitCreateDto.getSum())
                .currencyShortname(defaultLimitCurrency)
                .build();

        if (currentLimit == null ||
                !currentLimit.getSpentResetDatetime().getMonth().equals(OffsetDateTime.now().getMonth())) {
            newLimit.setCurrentSpent(BigDecimal.ZERO);
            newLimit.setSpentResetDatetime(OffsetDateTime.now());
        } else {
            newLimit.setCurrentSpent(currentLimit.getCurrentSpent());
            newLimit.setSpentResetDatetime(currentLimit.getSpentResetDatetime());
        }

        limitRepository.saveAndFlush(newLimit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LimitInfoDto> getAll(String account) {
        List<LimitInfoProjection> projections = limitRepository.findAllByAccountOrderByDateDesc(account);
        return limitInfoMapper.toLimitInfoDtoList(projections);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LimitInfoDto> getAllByExpenseCategory(String account, String expenseCategory) {
        List<LimitInfoProjection> projections =
                limitRepository.findAllByAccountAndCategoryOrderByDateDesc(account, expenseCategory);
        return limitInfoMapper.toLimitInfoDtoList(projections);
    }


    @Override
    public LimitEntity get(String account) {
        return null;
    }
}
