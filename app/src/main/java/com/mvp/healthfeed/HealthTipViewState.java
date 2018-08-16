package com.mvp.healthfeed;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class HealthTipViewState implements FeedViewState {

    public static HealthTipViewState create(int code,
                                            String title,
                                            String body,
                                            String tag,
                                            String supportText,
                                            int bannerImageVisibility,
                                            String bannerImagePath) {
        return new AutoValue_HealthTipViewState(code, title, body, tag, supportText, bannerImageVisibility, bannerImagePath);
    }

    public abstract int code();

    public abstract String title();

    public abstract String body();

    public abstract String tag();

    public abstract String supportText();

    public abstract int bannerImageVisibility();

    public abstract String bannerImagePath();

    @Override
    public final FeedViewState.Type type() {
        return Type.FEED_TIP;
    }
}
