package com.farly.farly.jsonmodel;

import java.util.List;

public class FeedItem {
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

    public FeedItem(String id, String name, String devName, String os, String status, String link, String icon, String priceApp, String moneyIcon, String moneyName, Double rewardAmount, String smallDescription, String smallDescriptionHTML, List<Action> actions, TotalPayout totalPayout, List<String> categories) {
        this.id = id;
        this.name = name;
        this.devName = devName;
        this.os = os;
        this.status = status;
        this.link = link;
        this.icon = icon;
        this.priceApp = priceApp;
        this.moneyIcon = moneyIcon;
        this.moneyName = moneyName;
        this.rewardAmount = rewardAmount;
        this.smallDescription = smallDescription;
        this.smallDescriptionHTML = smallDescriptionHTML;
        this.actions = actions;
        this.totalPayout = totalPayout;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getSmallDescriptionHTML() {
        return smallDescriptionHTML;
    }

    public void setSmallDescriptionHTML(String smallDescriptionHTML) {
        this.smallDescriptionHTML = smallDescriptionHTML;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriceApp() {
        return priceApp;
    }

    public void setPriceApp(String priceApp) {
        this.priceApp = priceApp;
    }

    public String getMoneyIcon() {
        return moneyIcon;
    }

    public void setMoneyIcon(String moneyIcon) {
        this.moneyIcon = moneyIcon;
    }

    public String getMoneyName() {
        return moneyName;
    }

    public void setMoneyName(String moneyName) {
        this.moneyName = moneyName;
    }

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public TotalPayout getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(TotalPayout totalPayout) {
        this.totalPayout = totalPayout;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", devName='" + devName + '\'' +
                ", os='" + os + '\'' +
                ", status='" + status + '\'' +
                ", link='" + link + '\'' +
                ", icon='" + icon + '\'' +
                ", priceApp='" + priceApp + '\'' +
                ", moneyIcon='" + moneyIcon + '\'' +
                ", moneyName='" + moneyName + '\'' +
                ", rewardAmount=" + rewardAmount +
                ", smallDescription='" + smallDescription + '\'' +
                ", smallDescriptionHTML='" + smallDescriptionHTML + '\'' +
                ", actions=" + actions +
                ", totalPayout=" + totalPayout +
                ", categories=" + categories +
                '}';
    }
}
