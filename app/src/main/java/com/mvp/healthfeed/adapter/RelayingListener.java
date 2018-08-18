package com.mvp.healthfeed.adapter;

import com.mvp.healthfeed.HealthAdViewState;
import com.mvp.healthfeed.HealthQnaViewState;
import com.mvp.healthfeed.HealthQuizViewState;
import com.mvp.healthfeed.HealthTipViewState;

import static com.mvp.common.Preconditions.checkNotNull;

public class RelayingListener implements HealthFeedAdapter.Listener {

    private HealthFeedAdapter.Listener listener = NO_OP;

    void wrap(HealthFeedAdapter.Listener listener) {
        this.listener = checkNotNull(listener);
    }

    @Override
    public void onTipFeedTapped(HealthTipViewState viewState) {
        listener.onTipFeedTapped(viewState);
    }

    @Override
    public void onAdFeedTapped(HealthAdViewState viewState) {
        listener.onAdFeedTapped(viewState);
    }

    @Override
    public void onQnAFeedTapped(HealthQnaViewState viewState) {
        listener.onQnAFeedTapped(viewState);
    }

    @Override
    public void onQuizFeedTapped(HealthQuizViewState viewState) {
        listener.onQuizFeedTapped(viewState);
    }
}
