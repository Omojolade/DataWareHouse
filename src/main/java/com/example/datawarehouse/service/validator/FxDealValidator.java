package com.example.datawarehouse.service.validator;

import com.example.datawarehouse.domain.FxDeal;
import com.example.datawarehouse.repository.FxDealRepository;
import com.example.datawarehouse.service.FxDealDTO;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FxDealValidator {
    private final ValidationBuilder validation = new ValidationBuilder();
    private final FxDealRepository fxRepository;

    public FxDealValidator(FxDealRepository fxRepository) {
        this.fxRepository = fxRepository;
    }

    public List<ValidatorError> validateFxDeal(FxDealDTO fxDealDTO) {
        if(fxDealDTO == null) {
            validation.addError("error.deal.null");
        }else {
            this.handleCurrencyFromValidation(fxDealDTO.getFrom())
                    .handleCurrencyToValidation(fxDealDTO.getTo())
                    .handleSameFxDealValidation(fxDealDTO.getFrom(), fxDealDTO.getTo())
                    .handleNullableTimestamp(fxDealDTO.getDealTimestamp())
                    .handleNullableDealAmount(fxDealDTO.getDealAmount());
        }
        return validation.build();
    }

    private FxDealValidator handleCurrencyFromValidation(String from) {
        if(!StringUtils.hasText(from) || isISOCurrencyCodeNotValid(from)){
            validation.addError("error.validation.currency.from.not.valid", from);
        }
        return this;
    }

    private FxDealValidator handleCurrencyToValidation(String to) {
        if(!StringUtils.hasText(to) || isISOCurrencyCodeNotValid(to)) {
            validation.addError("error.validation.currency.to.not.valid", to);
        }
        return this;
    }

    private FxDealValidator handleSameFxDealValidation(String from, String to) {
        if(Objects.nonNull(from) &&  Objects.nonNull(to) && from.equals(to)) {
            validation.addError("error.validation.currency.same", from);
        }
        return this;
    }

    private FxDealValidator handleNullableTimestamp(Instant timestamp) {
        if(Objects.isNull(timestamp )) {
            validation.addError("error.validation.timestamp.not.valid");
        }
        return this;
    }

    private FxDealValidator handleNullableDealAmount(BigDecimal amount) {
        if(amount == null) {
            validation.addError("error.validation.amount.not.valid");
        }
        return this;
    }

    private boolean isISOCurrencyCodeNotValid(String currencyCode) {
        return Currency.getAvailableCurrencies().stream().noneMatch(c -> c.getCurrencyCode().equals(currencyCode));
    }

    public List<ValidatorError> validateFxDealById(String id) {
        ValidationBuilder validationBuilder = new ValidationBuilder();
        Optional<FxDeal> optionalFxDeal = fxRepository.findById(id);
        if (optionalFxDeal.isEmpty()) {
            validationBuilder.addError("error.validation.fxDeal.not.exist", id);
        }
        return validationBuilder.build();
    }
}
