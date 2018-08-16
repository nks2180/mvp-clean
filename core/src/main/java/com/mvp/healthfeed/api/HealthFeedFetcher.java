package com.mvp.healthfeed.api;

import com.mvp.healthfeed.HealthFeed;

import io.reactivex.Single;

public interface HealthFeedFetcher {

    Single<HealthFeed> load();
}
