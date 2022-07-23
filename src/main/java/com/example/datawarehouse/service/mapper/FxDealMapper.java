package com.example.datawarehouse.service.mapper;

import com.example.datawarehouse.domain.FxDeal;
import com.example.datawarehouse.service.FxDealDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FxDealMapper extends EntityMapper<FxDeal, FxDealDTO> {

    FxDeal toEntity(FxDealDTO dto);

    FxDealDTO toDto(FxDeal entity);
}
