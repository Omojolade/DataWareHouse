package com.example.datawarehouse.service.impl;

import com.example.datawarehouse.domain.FxDeal;
import com.example.datawarehouse.repository.FxDealRepository;
import com.example.datawarehouse.service.FxDealDTO;
import com.example.datawarehouse.service.FxDealService;
import com.example.datawarehouse.service.mapper.FxDealMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FxDealServiceImpl implements FxDealService {
    private final FxDealRepository fxRepository;

    private final FxDealMapper fxDealMapper;

    public FxDealServiceImpl(FxDealRepository fxRepository, FxDealMapper fxDealMapper) {
        this.fxRepository = fxRepository;
        this.fxDealMapper = fxDealMapper;
    }

    @Override
    public FxDealDTO saveFxDeal(FxDealDTO fxDealDTO) {
        FxDeal fxDeal = fxDealMapper.toEntity(fxDealDTO);
        fxDeal.setUniqueId(UUID.randomUUID().toString());
        fxDeal = fxRepository.save(fxDeal);
        return fxDealMapper.toDto(fxDeal);
    }

    @Override
    public FxDealDTO getFxDealById(Long id) {
        return null;
    }

    @Override
    public List<FxDealService> getAllFxDeals() {
        return null;
    }
}
