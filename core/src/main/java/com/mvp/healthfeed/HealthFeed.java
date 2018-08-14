package com.mvp.healthfeed;

import com.mvp.healthfeed.api.ApiFeed;

import java.util.List;

public class HealthFeed {

    final List<Feed> healthStories;

    public HealthFeed(List<Feed> healthStories) {
        this.healthStories = healthStories;
    }
}
