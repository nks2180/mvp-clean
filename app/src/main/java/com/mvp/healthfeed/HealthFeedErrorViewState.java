package com.mvp.healthfeed;

import com.google.auto.value.AutoValue;

import java.util.Collections;

@AutoValue
public abstract class HealthFeedErrorViewState extends HealthFeedViewState {

    static HealthFeedErrorViewState createError(Throwable errorCause, Type errorType) {
        return new AutoValue_HealthFeedErrorViewState(Collections.<FeedViewState>emptyList(),
                errorCause,
                errorType);
    }

    public abstract Throwable cause();

    public abstract Type type();

    public enum Type {
        SERVER,
        NOT_FOUND,
        CONNECTION,
        UNKNOWN
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
