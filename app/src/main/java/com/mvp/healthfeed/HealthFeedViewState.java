package com.mvp.healthfeed;

import java.util.List;

public abstract class HealthFeedViewState {

    abstract List<FeedViewState> feedViewStates();

    public abstract void accept(Visitor visitor);

    public interface Visitor {

        void visit(HealthFeedIdleViewState idle);

        void visit(HealthFeedLoadingViewState loading);

        void visit(HealthFeedErrorViewState error);
    }

}
