package com.mvp.healthfeed;

import com.mvp.rx.Converter;

import java.util.ArrayList;
import java.util.List;

public class HealthFeedViewStateConverter implements Converter<HealthFeed, HealthFeedViewState>, Feed.Visitor {

    private final List<FeedViewState> viewStates = new ArrayList<>();

    @Override
    public HealthFeedViewState apply(HealthFeed healthFeed) throws Exception {
        for (Feed feed : healthFeed.healthStories) {
            feed.accept(this);
        }
        return HealthFeedViewState.create(viewStates);
    }

    @Override
    public void visit(HealthTipFeed tipFeed) {
        HealthTipViewState tipViewState = HealthTipViewState.create(tipFeed.code, tipFeed.title, tipFeed.body, tipFeed.tag, tipFeed.supportText, tipFeed.mediaList);
        viewStates.add(tipViewState);
    }

    @Override
    public void visit(HealthQuizFeed healthQuizFeed) {
        HealthQuizViewState quizViewState = HealthQuizViewState.create(healthQuizFeed.code, healthQuizFeed.title, healthQuizFeed.body, healthQuizFeed.tag, healthQuizFeed.supportText, healthQuizFeed.mediaList);
        viewStates.add(quizViewState);
    }

    @Override
    public void visit(HealthQnaFeed healthQnaFeed) {
        HealthQnaViewState qnaViewState = HealthQnaViewState.create(healthQnaFeed.code, healthQnaFeed.title, healthQnaFeed.body, healthQnaFeed.tag, healthQnaFeed.supportText, healthQnaFeed.mediaList);
        viewStates.add(qnaViewState);
    }

    @Override
    public void visit(HealthAdFeed healthAdFeed) {
        HealthAdViewState adViewState = HealthAdViewState.create(healthAdFeed.code, healthAdFeed.title, healthAdFeed.body, healthAdFeed.tag, healthAdFeed.supportText, healthAdFeed.mediaList);
        viewStates.add(adViewState);
    }

    @Override
    public void visit(UnknownFeed unknownFeed) {
        // no op
    }
}
