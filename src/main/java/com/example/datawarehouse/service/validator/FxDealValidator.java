package com.example.datawarehouse.service.validator;

import com.example.datawarehouse.domain.FxDeal;
import com.example.datawarehouse.repository.FxDealRepository;
import com.example.datawarehouse.service.FxDealDTO;
import org.springframework.util.StringUtils;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public class FxDealValidator {
    private final FxDealRepository fxRepository;

    public FxDealValidator(FxDealRepository fxRepository) {
        this.fxRepository = fxRepository;
    }

    public List<ValidatorError> validateFxDeal(FxDealDTO fxDealDTO) {
        ValidationBuilder validation = new ValidationBuilder();
        if(fxDealDTO == null) {
            validation.addError("error.deal.null");
        }else {
            if(!StringUtils.hasText(fxDealDTO.getFrom()) || !isValidISOCurrencyCode(fxDealDTO.getFrom())){
                validation.addError("error.deal.currency.from.not.valid", fxDealDTO.getFrom());
            }
            if(!StringUtils.hasText(fxDealDTO.getTo()) || !isValidISOCurrencyCode(fxDealDTO.getTo())) {
                validation.addError("error.deal.currency.to.not.valid", fxDealDTO.getTo());
            }
            if(fxDealDTO.getTo().equals(fxDealDTO.getFrom())) {
                validation.addError("error.deal.currency.same", fxDealDTO.getTo());
            }
            if(fxDealDTO.getDealTimestamp() == null) {
                validation.addError("error.deal.timestamp.not.valid");
            }
            if(fxDealDTO.getDealAmount() == null) {
                validation.addError("error.deal.amount.not.valid");
            }
        }
        return validation.build();
    }

    private boolean isValidISOCurrencyCode(String currencyCode) {
        return Currency.getAvailableCurrencies().stream().anyMatch(c -> c.getCurrencyCode().equals(currencyCode));
    }

    public List<ValidatorError> validateFxDealById(Long id) {
        ValidationBuilder validationBuilder = new ValidationBuilder();
        Optional<FxDeal> prescription = fxRepository.findById(id);
        if (prescription.isEmpty()) {
            validationBuilder.addError("error.validation.fxDeal.not.exist", id);
        }
        return validationBuilder.build();
    }
}
