package com.example.datawarehouse.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

public class FxDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "unique_id", nullable = false, unique = true)
    private String uniqueId;

    @Column(name = "from", nullable = false)
    private Currency from;

    @Column(name = "to", nullable = false)
    private Currency to;

    @NotNull
    @Column(name = "deal_timestamp", nullable = false)
    private Instant dealTimestamp;

    @Column(name = "deal_amount", precision = 21, scale = 2)
    private BigDecimal dealAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public Instant getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(Instant dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }
}
