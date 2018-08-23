package com.mvp.healthfeed;

public class HealthFeedDisplayer {

    private final HealthFeedView view;


    public HealthFeedDisplayer(HealthFeedView view) {
        this.view = view;
    }

    void show(HealthFeedViewState viewState) {
        viewState.accept(new HealthFeedViewState.Visitor() {
            @Override
            public void visit(HealthFeedIdleViewState idle) {
                view.show(idle.feedViewStates());
            }

            @Override
            public void visit(HealthFeedLoadingViewState loading) {
                view.showLoading();
            }

            @Override
            public void visit(HealthFeedErrorViewState error) {
                view.showError();
            }
        });
    }

    public void setListener(HealthFeedView.Listener listener) {
        view.setListener(listener);
    }
}
