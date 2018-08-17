package com.mvp.healthfeed;

import com.google.auto.value.AutoValue;
import com.mvp.common.Optional;

@AutoValue
public abstract class HealthTipFeed implements Feed {

    public static HealthTipFeed create(int code,
                                      String title,
                                      String body,
                                      String tag,
                                      String supportText,
                                      Optional<String> imageUrl) {
        return new AutoValue_HealthTipFeed(code, title, body, tag, supportText, imageUrl);
    }

    public abstract int code();

    public abstract String title();

    public abstract String body();

    public abstract String tag();

    public abstract String supportText();

    public abstract Optional<String> imageUrl();

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
