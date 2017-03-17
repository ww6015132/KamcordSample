package com.example.weiwang.kamcordsample.Models;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.http.Url;

/**
 * Created by weiwang on 3/15/17.
 */

public class BadgeModel {
    private UrlModel small;
    private UrlModel medium;
    private UrlModel large;

    public UrlModel getSmall() {
        return small;
    }

    public void setSmall(UrlModel small) {
        this.small = small;
    }

    public UrlModel getMedium() {
        return medium;
    }

    public void setMedium(UrlModel medium) {
        this.medium = medium;
    }

    public UrlModel getLarge() {
        return large;
    }

    public void setLarge(UrlModel large) {
        this.large = large;
    }
}
