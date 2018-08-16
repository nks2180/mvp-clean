package com.mvp.healthfeed;

import com.mvp.healthfeed.api.HealthFeedFetcher;
import com.mvp.rx.SchedulingStrategy;

import io.reactivex.Single;

public class HealthFeedUseCase {

    private final HealthFeedFetcher fetcher;
    private final HealthFeedViewStateConverter converter;
    private final SchedulingStrategy.Factory schedulingStrategyFactory;

    HealthFeedUseCase(HealthFeedFetcher fetcher,
                      HealthFeedViewStateConverter converter,
                      SchedulingStrategy.Factory schedulingStrategyFactory) {
        this.fetcher = fetcher;
        this.converter = converter;
        this.schedulingStrategyFactory = schedulingStrategyFactory;
    }

    Single<HealthFeedViewState> healthFeed() {
        return fetcher.load()
                .map(converter)
                .compose(schedulingStrategyFactory.<HealthFeedViewState>create());
    }
}
