package com.example.datawarehouse.service.dto;

import com.example.datawarehouse.DataWarehouseApplication;
import com.example.datawarehouse.service.FxDealDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FxDealDTO.class)
class FxDealDTOTest {
    @Test
    void testConstructor() {
        FxDealDTO actualFxDealDTO = new FxDealDTO();
        BigDecimal value = BigDecimal.valueOf(42L);
        actualFxDealDTO.setDealAmount(value);
        actualFxDealDTO.setDealTimestamp(null);
        actualFxDealDTO.setFrom("GBP");
        actualFxDealDTO.setTo("GBP");
        actualFxDealDTO.setId("42");
        assertSame(value, actualFxDealDTO.getDealAmount());
        assertNull(actualFxDealDTO.getDealTimestamp());
        assertNotEquals("NGN", actualFxDealDTO.getFrom());
        assertNotEquals("NGN", actualFxDealDTO.getTo());
        assertNotEquals("43", actualFxDealDTO.getId());
    }
}

