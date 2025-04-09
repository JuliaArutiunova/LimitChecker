package com.example.limit_checker.service;

import com.example.limit_checker.dto.LimitCreateDto;
import com.example.limit_checker.dto.LimitInfoDto;
import com.example.limit_checker.repository.api.ILimitRepository;
import com.example.limit_checker.repository.entity.LimitEntity;
import com.example.limit_checker.repository.projection.LimitInfoProjection;
import com.example.limit_checker.service.api.ILimitService;
import com.example.limit_checker.service.mapper.LimitInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitService implements ILimitService {

    private final ILimitRepository limitRepository;
    private final LimitInfoMapper limitInfoMapper;

    public LimitService(ILimitRepository limitRepository, LimitInfoMapper limitInfoMapper) {
        this.limitRepository = limitRepository;
        this.limitInfoMapper = limitInfoMapper;
    }

    @Override
    public void create(String account, LimitCreateDto limitCreateDto) {

    }

    @Override
    public List<LimitInfoDto> getAll(String account) {
        List<LimitInfoProjection> projections = limitRepository.findAllByAccountOrderByDateDesc(account);
        return limitInfoMapper.toLimitInfoDtoList(projections);
    }

    @Override
    public List<LimitInfoDto> getAllByExpenseCategory(String account, String expenseCategory) {
        List<LimitInfoProjection> projections =
                limitRepository.findAllByAccountAndCategoryOrderByDateDesc(account,expenseCategory);
        return limitInfoMapper.toLimitInfoDtoList(projections);
    }


    @Override
    public LimitEntity get(String account) {
        return null;
    }
}
