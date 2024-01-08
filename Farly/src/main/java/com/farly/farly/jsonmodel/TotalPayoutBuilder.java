package com.farly.farly.jsonmodel;

public class TotalPayoutBuilder {
    private Double amount;
    private String currency;

    public TotalPayoutBuilder setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public TotalPayoutBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public TotalPayout createTotalPayout() {
        return new TotalPayout(amount, currency);
    }
}