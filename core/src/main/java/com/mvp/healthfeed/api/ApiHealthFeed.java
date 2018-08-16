package com.mvp.healthfeed.api;

import java.util.List;

class ApiHealthFeed {

    final ApiStatus status;
    final List<ApiFeed> healthStories;


    ApiHealthFeed(ApiStatus status, List<ApiFeed> feeds) {
        this.status = status;
        this.healthStories = feeds;
    }
}
