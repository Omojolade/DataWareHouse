package com.example.datawarehouse.config;


import com.example.datawarehouse.repository.FxDealRepository;
import com.example.datawarehouse.service.mapper.FxDealMapper;
import com.example.datawarehouse.service.validator.FxDealValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FxDealConfiguration {

    @Bean
    public FxDealValidator fxDealValidator(FxDealRepository fxDealRepository) {
        return new FxDealValidator(fxDealRepository);
    }
}
