package com.mvp.healthfeed;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class HealthFeedIdleViewState extends HealthFeedViewState {

    public static HealthFeedIdleViewState create(List<FeedViewState> feedViewStates) {
        return new AutoValue_HealthFeedIdleViewState(feedViewStates);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
