package com.mvp.healthfeed.api;

import com.mvp.healthfeed.Feed;

import java.util.List;

public class ApiAdFeed implements ApiFeed {

    public final int code;
    public final String title;
    public final String body;
    public final String tag;
    public final String supportText;
    public final List<ApiMedia> mediaList;

    public ApiAdFeed(int code,
                     String title,
                     String body,
                     String tag,
                     String supportText,
                     List<ApiMedia> mediaList) {
        this.code = code;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.supportText = supportText;
        this.mediaList = mediaList;
    }

    @Override
    public Feed accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
