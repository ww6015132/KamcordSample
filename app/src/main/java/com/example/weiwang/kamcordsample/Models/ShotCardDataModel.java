package com.example.weiwang.kamcordsample.Models;

import java.util.List;

/**
 * Created by weiwang on 3/15/17.
 */

public class ShotCardDataModel {
    private String id;
    private String videoId;
    private String username;
    private List<BadgeModel> badgesv2;
    private int heartCount;
    private ShotThumbnail shotThumbnail;
    private PlayModel play;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BadgeModel> getBadgesv2() {
        return badgesv2;
    }

    public void setBadgesv2(List<BadgeModel> badgesv2) {
        this.badgesv2 = badgesv2;
    }

    public int getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }

    public ShotThumbnail getShotThumbnail() {
        return shotThumbnail;
    }

    public void setShotThumbnail(ShotThumbnail shotThumbnail) {
        this.shotThumbnail = shotThumbnail;
    }

    public PlayModel getPlay() {
        return play;
    }

    public void setPlay(PlayModel play) {
        this.play = play;
    }
}
