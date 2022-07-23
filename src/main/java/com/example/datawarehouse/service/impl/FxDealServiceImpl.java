package com.example.datawarehouse.service.impl;

import com.example.datawarehouse.aop.response.error.ValidationException;
import com.example.datawarehouse.domain.FxDeal;
import com.example.datawarehouse.repository.FxDealRepository;
import com.example.datawarehouse.service.FxDealDTO;
import com.example.datawarehouse.service.FxDealService;
import com.example.datawarehouse.service.mapper.FxDealMapper;
import com.example.datawarehouse.service.validator.FxDealValidator;
import com.example.datawarehouse.service.validator.ValidatorError;
import org.springframework.stereotype.Service;
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

    private final FxDealValidator fxValidator;

    public FxDealServiceImpl(FxDealRepository fxRepository, FxDealMapper fxMapper, FxDealValidator fxValidator) {
        this.fxRepository = fxRepository;
        this.fxMapper = fxMapper;
        this.fxValidator = fxValidator;
    }

    @Override
    public FxDealDTO saveFxDeal(FxDealDTO fxDealDTO) {
        String correlationId = UUID.randomUUID().toString();
        List<ValidatorError> validationErrors = fxValidator.validateFxDeal(fxDealDTO);
        if(!CollectionUtils.isEmpty(validationErrors)) throw new ValidationException(validationErrors);
        FxDeal fxDeal = fxMapper.toEntity(fxDealDTO);
        fxDeal.setId(correlationId);
        fxDeal = fxRepository.save(fxDeal);
        return fxMapper.toDto(fxDeal);
    }

    @Override
    public FxDealDTO getFxDealById(String id) {
        List<ValidatorError> validationErrors = fxValidator.validateFxDealById(id);
        if(!CollectionUtils.isEmpty(validationErrors)) throw new ValidationException(validationErrors);
        Optional<FxDealDTO> optionalFxDeal = fxRepository.findById(id).map(fxMapper::toDto);
        return optionalFxDeal.orElseThrow(() -> new ValidationException(List.of()));
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
