package com.example.datawarehouse.service.mapper;

import com.example.datawarehouse.domain.FxDeal;
import com.example.datawarehouse.service.FxDealDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public class FxDealMapper implements EntityMapper<FxDealDTO, FxDeal>{
    @Override
    public FxDeal toEntity(FxDealDTO dto) {
        return null;
    }

    @Override
    public FxDealDTO toDto(FxDeal entity) {
        return null;
    }
}
