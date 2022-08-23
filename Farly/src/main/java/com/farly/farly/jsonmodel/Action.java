package com.farly.farly.jsonmodel;

public class Action {
    private String id;
    private Double amount;
    private String text;
    private String html;

    public Action(String id, Double amount, String text, String html) {
        this.id = id;
        this.amount = amount;
        this.text = text;
        this.html = html;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", text='" + text + '\'' +
                ", html='" + html + '\'' +
                '}';
    }
}
