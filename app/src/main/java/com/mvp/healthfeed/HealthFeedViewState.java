package com.mvp.healthfeed;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class HealthFeedViewState {

    public static HealthFeedViewState create(List<FeedViewState> componentViewStates) {
        return new AutoValue_HealthFeedViewState(componentViewStates);
    }

//    public HealthFeedViewState withComponents(List<FeedViewState> feedViewStates) {
//        return create(feedViewStates);
//    }

    public abstract List<FeedViewState> feedViewStates();

}
