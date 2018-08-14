package com.mvp.healthfeed.api;

import java.util.List;

public class ApiHealthFeed {

    final ApiStatus status;
    public final List<ApiFeed> healthStories;


    public ApiHealthFeed(ApiStatus status, List<ApiFeed> feeds) {
        this.status = status;
        this.healthStories = feeds;
    }
}
