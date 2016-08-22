package ru.gopromo.testappgp.data_model;

import android.os.Bundle;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Item {

    private static String KEY_LINK = "linkItem";

    @Element(name = "title")
    private String title;
    @Element(name = "link")
    private String link;
    @Element(name = "pubDate")
    private String pubDate;
    @Element(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }

    public Bundle linkToBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_LINK, link);
        return bundle;
    }

    public static String getLinkFromBundle(Bundle aBundle) {
        return aBundle.getString(KEY_LINK);
    }
}
