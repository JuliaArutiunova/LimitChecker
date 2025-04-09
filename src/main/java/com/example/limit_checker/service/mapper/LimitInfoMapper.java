package com.example.limit_checker.service.mapper;

import com.example.limit_checker.dto.LimitInfoDto;
import com.example.limit_checker.repository.projection.LimitInfoProjection;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface LimitInfoMapper {
    LimitInfoDto toLimitInfoDto(LimitInfoProjection projection);

    List<LimitInfoDto> toLimitInfoDtoList(List<LimitInfoProjection> projections);
}
