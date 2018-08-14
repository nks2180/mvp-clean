package com.mvp.healthfeed;

import java.util.List;

public class HealthAdFeed implements Feed{

    final int code;
    final String title;
    final String body;
    final String tag;
    final String supportText;
    final List<Media> mediaList;

    public HealthAdFeed(int code,
                        String title,
                        String body,
                        String tag,
                        String supportText,
                        List<Media> mediaList) {
        this.code = code;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.supportText = supportText;
        this.mediaList = mediaList;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
