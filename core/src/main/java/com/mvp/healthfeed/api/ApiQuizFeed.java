package com.mvp.healthfeed.api;

import com.mvp.healthfeed.Feed;

import java.util.List;

class ApiQuizFeed implements ApiFeed {

    final int code;
    final String title;
    final String body;
    final String tag;
    final String supportText;
    final List<ApiMedia> mediaList;

    ApiQuizFeed(int code,
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
