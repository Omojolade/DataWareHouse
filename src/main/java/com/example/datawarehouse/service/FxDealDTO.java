package com.example.datawarehouse.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
public class FxDealDTO {
    private String id;

    private String from;

    private String to;

    private Instant dealTimestamp;

    private BigDecimal dealAmount;
}
