package com.example.datawarehouse.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest(classes = FxDeal.class)
class FxDealTest {
    @Test
    void testConstructor() {
        FxDeal actualFxDeal = new FxDeal();
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualFxDeal.setDealAmount(valueOfResult);
        actualFxDeal.setDealTimestamp(null);
        actualFxDeal.setFrom(null);
        actualFxDeal.setId("11");
        actualFxDeal.setTo(null);
        assertSame(valueOfResult, actualFxDeal.getDealAmount());
        assertNull(actualFxDeal.getDealTimestamp());
        assertNull(actualFxDeal.getFrom());
        assertNull(actualFxDeal.getTo());
    }
}

