package com.farly.farly.jsonmodel;

public class TotalPayout {
    private Double amount;
    private String currency;

    public TotalPayout(Double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "TotalPayout{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
