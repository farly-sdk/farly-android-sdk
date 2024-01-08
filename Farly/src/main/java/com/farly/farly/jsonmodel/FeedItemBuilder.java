package com.farly.farly.jsonmodel;

import java.util.List;

public class FeedItemBuilder {
    private String id;
    private String name;
    private String devName;
    private String os;
    private String status;
    private String link;
    private String icon;
    private String priceApp;
    private String moneyIcon;
    private String moneyName;
    private Double rewardAmount;
    private String smallDescription;
    private String smallDescriptionHTML;
    private List<Action> actions;
    private TotalPayout totalPayout;
    private List<String> categories;

    public FeedItemBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public FeedItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FeedItemBuilder setDevName(String devName) {
        this.devName = devName;
        return this;
    }

    public FeedItemBuilder setLink(String link) {
        this.link = link;
        return this;
    }

    public FeedItemBuilder setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public FeedItemBuilder setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
        return this;
    }

    public FeedItemBuilder setSmallDescriptionHTML(String smallDescriptionHTML) {
        this.smallDescriptionHTML = smallDescriptionHTML;
        return this;
    }

    public FeedItemBuilder setActions(List<Action> actions) {
        this.actions = actions;
        return this;
    }

    public FeedItemBuilder setOs(String os) {
        this.os = os;
        return this;
    }

    public FeedItemBuilder setPriceApp(String priceApp) {
        this.priceApp = priceApp;
        return this;
    }

    public FeedItemBuilder setMoneyIcon(String moneyIcon) {
        this.moneyIcon = moneyIcon;
        return this;
    }

    public FeedItemBuilder setMoneyName(String moneyName) {
        this.moneyName = moneyName;
        return this;
    }

    public FeedItemBuilder setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
        return this;
    }

    public FeedItemBuilder setTotalPayout(TotalPayout totalPayout) {
        this.totalPayout = totalPayout;
        return this;
    }

    public FeedItemBuilder setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public FeedItemBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public FeedItem createFeedItem() {
        return new FeedItem(id, name, devName, os, status, link, icon, priceApp, moneyIcon, moneyName, rewardAmount, smallDescription, smallDescriptionHTML, actions, totalPayout, categories);
    }
}