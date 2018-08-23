package com.mvp.healthfeed;

import com.mvp.healthfeed.api.HealthFeedFetcher;
import com.mvp.rx.SchedulingStrategy;

import io.reactivex.Observable;

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

    Observable<HealthFeedViewState> healthFeed() {
        HealthFeedViewState loading = HealthFeedLoadingViewState.createLoading();
        return fetcher.load()
                .toObservable()
                .map(converter)
                .startWith(loading)
                .compose(new HealthFeedViewStateErrorConverter())
                .compose(schedulingStrategyFactory.<HealthFeedViewState>create());
    }
}
