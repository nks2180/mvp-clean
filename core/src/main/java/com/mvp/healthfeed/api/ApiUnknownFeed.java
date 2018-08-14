package com.mvp.healthfeed.api;


import com.mvp.healthfeed.Feed;

class ApiUnknownFeed implements ApiFeed {


    @Override
    public Feed accept(Visitor visitor) {
        return Feed.EMPTY;
    }
}
