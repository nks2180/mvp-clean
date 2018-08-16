package com.mvp.healthfeed;

import android.view.View;

import com.mvp.common.Optional;
import com.mvp.rx.Converter;

import java.util.ArrayList;
import java.util.List;

public class HealthFeedViewStateConverter implements Converter<HealthFeed, HealthFeedViewState>, Feed.Visitor {

    private static final String INVALID_IMAGE_URL = "http://image.url";
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
        HealthTipViewState tipViewState = HealthTipViewState.create(tipFeed.code,
                tipFeed.title,
                tipFeed.body,
                tipFeed.tag,
                tipFeed.supportText,
                imagaeVisbilityFrom(tipFeed.imageUrl),
                imagaeUrlFrom(tipFeed.imageUrl));
        viewStates.add(tipViewState);
    }

    @Override
    public void visit(HealthQuizFeed healthQuizFeed) {
        HealthQuizViewState quizViewState = HealthQuizViewState.create(healthQuizFeed.code,
                healthQuizFeed.title,
                healthQuizFeed.body,
                healthQuizFeed.tag,
                healthQuizFeed.supportText,
                imagaeVisbilityFrom(healthQuizFeed.imageUrl),
                imagaeUrlFrom(healthQuizFeed.imageUrl));
        viewStates.add(quizViewState);
    }

    @Override
    public void visit(HealthQnaFeed healthQnaFeed) {
        HealthQnaViewState qnaViewState = HealthQnaViewState.create(healthQnaFeed.code,
                healthQnaFeed.title,
                healthQnaFeed.body,
                imagaeVisbilityFrom(healthQnaFeed.imageUrl),
                imagaeUrlFrom(healthQnaFeed.imageUrl));
        viewStates.add(qnaViewState);
    }

    @Override
    public void visit(HealthAdFeed healthAdFeed) {
        HealthAdViewState adViewState = HealthAdViewState.create(healthAdFeed.code,
                healthAdFeed.title,
                healthAdFeed.body,
                healthAdFeed.tag,
                healthAdFeed.supportText,
                imagaeVisbilityFrom(healthAdFeed.imageUrl),
                imagaeUrlFrom(healthAdFeed.imageUrl));
        viewStates.add(adViewState);
    }

    @Override
    public void visit(UnknownFeed unknownFeed) {
        // no op
    }

    private int imagaeVisbilityFrom(Optional<String> image) {
        return image.isPresent() ? View.VISIBLE : View.GONE;
    }

    private String imagaeUrlFrom(Optional<String> image) {
        return image.isPresent() ? image.get() : INVALID_IMAGE_URL;
    }

}
