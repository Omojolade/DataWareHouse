package com.example.datawarehouse.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "fx_deal")
@Getter
@Setter
@EqualsAndHashCode
public class FxDeal {
    @Id
    @Column
    private String id;

    @NotNull(message = "error.deal.null")
    @Column(name = "currency_from", nullable = false)
    private Currency from;

    @NotNull(message = "error.deal.null")
    @Column(name = "currency_to", nullable = false)
    private Currency to;

    @NotNull
    @Column(name = "deal_timestamp", nullable = false)
    private Instant dealTimestamp;

    @Column(name = "deal_amount", precision = 21, scale = 2)
    private BigDecimal dealAmount;


}
