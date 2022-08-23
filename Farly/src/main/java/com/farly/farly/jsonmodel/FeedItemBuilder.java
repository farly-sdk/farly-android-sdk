package com.farly.farly.jsonmodel;

import java.util.List;

public class FeedItemBuilder {
    private String id;
    private String name;
    private String devName;
    private String link;
    private String icon;
    private String smallDescription;
    private String smallDescriptionHTML;
    private List<Action> actions;

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

    public FeedItem createFeedItem() {
        return new FeedItem(id, name, devName, link, icon, smallDescription, smallDescriptionHTML, actions);
    }
}