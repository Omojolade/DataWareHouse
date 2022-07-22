package com.example.datawarehouse.service;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

public class FxDealDTO {
    private Long id;

    private String uniqueId;

    private String from;

    private String to;

    private Instant dealTimestamp;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
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
