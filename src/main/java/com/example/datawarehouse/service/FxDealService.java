package com.example.datawarehouse.service;

import java.util.List;

public interface FxDealService {

    FxDealDTO saveFxDeal(FxDealDTO fxDealDTO);

    List<FxDealDTO> saveMultipleFxDeals(List<FxDealDTO> fxDealDTOList);

    FxDealDTO getFxDealById(String id);

    List<FxDealDTO> getAllFxDeals();

}
