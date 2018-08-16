package com.mvp.healthfeed.api;

import com.mvp.healthfeed.Feed;

interface ApiFeed {

    ApiFeed UNKNOWN = new ApiUnknownFeed();

    Feed accept(Visitor visitor);

    interface Visitor {

        Feed visit(ApiTipFeed apiTipFeed);

        Feed visit(ApiQnAFeed apiQnAFeed);

        Feed visit(ApiQuizFeed quizFeed);

        Feed visit(ApiAdFeed adFeed);
    }

}
