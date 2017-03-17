package com.example.weiwang.kamcordsample.Models;

import java.util.List;

/**
 * Created by weiwang on 3/15/17.
 */

public class GroupModel {
    private List<CardModel> cards;
    private String nextPage;
    private String feedId;

    public List<CardModel> getCards() {
        return cards;
    }

    public void setCards(List<CardModel> cards) {
        this.cards = cards;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
