package com.mvp.healthfeed;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class HealthQnaViewState implements FeedViewState {

    public static HealthQnaViewState create(int code,
                                            String title,
                                            String body,
                                            List<Media> mediaList) {
        return new AutoValue_HealthQnaViewState(code, title, body, mediaList);
    }

    public abstract int code();

    public abstract String title();

    public abstract String body();

    public abstract List<Media> mediaList();

    @Override
    public final FeedViewState.Type type() {
        return FeedViewState.Type.FEED_QNA;
    }
}
