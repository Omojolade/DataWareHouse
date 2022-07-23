package com.example.datawarehouse.service;

import java.util.List;

public interface FxDealService {

    FxDealDTO saveFxDeal(FxDealDTO fxDealDTO);

    FxDealDTO getFxDealById(String id);

    List<FxDealDTO> getAllFxDeals();

}
