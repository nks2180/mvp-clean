package com.mvp.healthfeed.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ApiHealthFeedBuilder {

    private ApiStatus status = new ApiStatus(200, "message");
    private List<ApiFeed> feeds;

    ApiHealthFeedBuilder() {
        this.feeds = new ArrayList<>();
    }

    ApiHealthFeedBuilder withStatus(ApiStatus status) {
        this.status = status;
        return this;
    }

    ApiHealthFeedBuilder withStatusCode(int statusCode, String message) {
        this.status = new ApiStatus(statusCode, message);
        return this;
    }

    ApiHealthFeedBuilder withFeed(ApiTipFeed tipFeed) {
        feeds.add(tipFeed);
        return this;
    }

    ApiHealthFeedBuilder withFeed(ApiQnAFeed qnAFeed) {
        feeds.add(qnAFeed);
        return this;
    }

    ApiHealthFeedBuilder withFeed(ApiQuizFeed quizFeed) {
        feeds.add(quizFeed);
        return this;
    }

    ApiHealthFeedBuilder withFeed(ApiAdFeed adFeed) {
        feeds.add(adFeed);
        return this;
    }

    ApiHealthFeedBuilder withFeeds(ApiFeed... feeds) {
        this.feeds = Arrays.asList(feeds);
        return this;
    }

    ApiHealthFeed build() {
        return new ApiHealthFeed(status, feeds);
    }
}
