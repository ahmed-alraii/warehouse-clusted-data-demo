package com.ahmedalraii.warehouseClusteredData.models;

import jakarta.persistence.*;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fromCurrency;

    private String toCurrency;

    private String dealTimestamp;

    private double amount;

    public Long getId() {
        return id;
    }

    public Deal(){

    }

    public Deal( String fromCurrencyOrdering, String toCurrency, String dealTimestamp, double dealAmountOrdering) {

        this.fromCurrency = fromCurrencyOrdering;
        this.toCurrency = toCurrency;
        this.dealTimestamp = dealTimestamp;
        this.amount = dealAmountOrdering;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(String dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}
