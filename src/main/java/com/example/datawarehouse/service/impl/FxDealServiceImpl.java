package com.example.datawarehouse.service.impl;

import com.example.datawarehouse.domain.FxDeal;
import com.example.datawarehouse.repository.FxDealRepository;
import com.example.datawarehouse.service.FxDealDTO;
import com.example.datawarehouse.service.FxDealService;
import com.example.datawarehouse.service.mapper.FxDealMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FxDealServiceImpl implements FxDealService {
    private final FxDealRepository fxRepository;

    private final FxDealMapper fxMapper;

    public FxDealServiceImpl(FxDealRepository fxRepository, FxDealMapper fxMapper) {
        this.fxRepository = fxRepository;
        this.fxMapper = fxMapper;
    }

    @Override
    public FxDealDTO saveFxDeal(FxDealDTO fxDealDTO) {
        FxDeal fxDeal = fxMapper.toEntity(fxDealDTO);
        fxDeal.setUniqueId(UUID.randomUUID().toString());
        fxDeal = fxRepository.save(fxDeal);
        return fxMapper.toDto(fxDeal);
    }

    @Override
    public FxDealDTO getFxDealById(Long id) {
       Optional<FxDeal> fxDeal = fxRepository.findById(id);
       return fxMapper.toDto(fxDeal.get());
    }

    @Override
    public List<FxDealDTO> getAllFxDeals() {
        List<FxDeal> fxDeals = fxRepository.findAll();
        return fxDeals.stream().map(fxMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<FxDealDTO> saveMultipleFxDeals(List<FxDealDTO> fxDealDTOList) {
        List<FxDealDTO> fxDeals = new ArrayList<>();
        for (FxDealDTO fxDealDTO : fxDealDTOList) {
            fxDeals.add(saveFxDeal(fxDealDTO));
        }
        return fxDeals;
    }
}
