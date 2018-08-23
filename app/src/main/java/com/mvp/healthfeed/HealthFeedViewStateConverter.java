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
        return HealthFeedIdleViewState.create(viewStates);
    }

    @Override
    public void visit(TipFeed tipFeed) {
        HealthTipViewState tipViewState = HealthTipViewState.create(tipFeed.code(),
                tipFeed.title(),
                tipFeed.body(),
                tipFeed.tag(),
                tipFeed.supportText(),
                imageVisbilityFrom(tipFeed.imageUrl()),
                imagaeUrlFrom(tipFeed.imageUrl()));
        viewStates.add(tipViewState);
    }

    @Override
    public void visit(QuizFeed quizFeed) {
        HealthQuizViewState quizViewState = HealthQuizViewState.create(quizFeed.code(),
                quizFeed.title(),
                quizFeed.body(),
                quizFeed.tag(),
                quizFeed.supportText(),
                imageVisbilityFrom(quizFeed.imageUrl()),
                imagaeUrlFrom(quizFeed.imageUrl()));
        viewStates.add(quizViewState);
    }

    @Override
    public void visit(QnaFeed qnaFeed) {
        HealthQnaViewState qnaViewState = HealthQnaViewState.create(qnaFeed.code(),
                qnaFeed.title(),
                qnaFeed.body(),
                imageVisbilityFrom(qnaFeed.imageUrl()),
                imagaeUrlFrom(qnaFeed.imageUrl()));
        viewStates.add(qnaViewState);
    }

    @Override
    public void visit(AdFeed adFeed) {
        HealthAdViewState adViewState = HealthAdViewState.create(adFeed.code(),
                adFeed.title(),
                adFeed.body(),
                adFeed.tag(),
                adFeed.supportText(),
                imageVisbilityFrom(adFeed.imageUrl()),
                imagaeUrlFrom(adFeed.imageUrl()));
        viewStates.add(adViewState);
    }

    @Override
    public void visit(UnknownFeed unknownFeed) {
        // no op
    }

    private int imageVisbilityFrom(Optional<String> image) {
        return image.isPresent() ? View.VISIBLE : View.GONE;
    }

    private String imagaeUrlFrom(Optional<String> image) {
        return image.isPresent() ? image.get() : INVALID_IMAGE_URL;
    }

}
