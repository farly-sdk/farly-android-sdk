package com.farly.farly.jsonmodel;

import java.util.List;

public class FeedItem {
    private String id;
    private String name;
    private String devName;
    private String link;
    private String icon;
    private String smallDescription;
    private String smallDescriptionHTML;
    private List<Action> actions;

    public FeedItem(String id, String name, String devName, String link, String icon, String smallDescription, String smallDescriptionHTML, List<Action> actions) {
        this.id = id;
        this.name = name;
        this.devName = devName;
        this.link = link;
        this.icon = icon;
        this.smallDescription = smallDescription;
        this.smallDescriptionHTML = smallDescriptionHTML;
        this.actions = actions;
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

    @Override
    public String toString() {
        return "FeedItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", devName='" + devName + '\'' +
                ", link='" + link + '\'' +
                ", icon='" + icon + '\'' +
                ", smallDescription='" + smallDescription + '\'' +
                ", smallDescriptionHTML='" + smallDescriptionHTML + '\'' +
                ", actions=" + actions +
                '}';
    }
}
