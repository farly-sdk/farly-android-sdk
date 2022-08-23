package com.farly.farly.jsonmodel;

public class ActionBuilder {
    private String id;
    private Double amount;
    private String text;
    private String html;

    public ActionBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public ActionBuilder setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public ActionBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public ActionBuilder setHtml(String html) {
        this.html = html;
        return this;
    }

    public Action createAction() {
        return new Action(id, amount, text, html);
    }
}