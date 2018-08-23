package com.mvp.healthfeed;

import com.google.auto.value.AutoValue;

import java.util.Collections;

@AutoValue
public abstract class HealthFeedLoadingViewState extends HealthFeedViewState {

    static HealthFeedLoadingViewState createLoading() {
        return new AutoValue_HealthFeedLoadingViewState(Collections.<FeedViewState>emptyList());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
