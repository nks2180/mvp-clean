package com.mvp.healthfeed;

import com.mvp.common.Optional;

import java.util.List;

public class HealthAdFeed implements Feed {

    final int code;
    final String title;
    final String body;
    final String tag;
    final String supportText;
    Optional<String> imageUrl;

    public HealthAdFeed(int code,
                 String title,
                 String body,
                 String tag,
                 String supportText,
                 Optional<String> imageUrl) {
        this.code = code;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.supportText = supportText;
        this.imageUrl = imageUrl;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
