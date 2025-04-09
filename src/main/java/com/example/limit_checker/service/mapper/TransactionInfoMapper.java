package com.example.limit_checker.service.mapper;

import com.example.limit_checker.dto.TransactionInfoDto;
import com.example.limit_checker.repository.projection.TransactionInfoProjection;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface TransactionInfoMapper {
    TransactionInfoDto toTransactionInfoDto(TransactionInfoProjection projection);

    List<TransactionInfoDto> toTransactionInfoDtoList(List<TransactionInfoProjection> projections);
}
