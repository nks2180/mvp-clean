package com.mvp.healthfeed;

import android.util.Log;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;

class HealthFeedActivityPresenter {

    private final HealthFeedUseCase useCase;
    private final HealthFeedView view;
    private Disposable disposable = Disposables.empty();
    private final HealthFeedView.Listener listener = new HealthFeedView.Listener() {

        @Override
        public void onQuizFeedTapped(HealthQuizViewState adViewState) {
            Log.d("Presenter", "onQuizFeedTapped");
        }

        @Override
        public void onQnAFeedTapped(HealthQnaViewState adViewState) {
            Log.d("Presenter", "onQnAFeedTapped");
        }

        @Override
        public void onAdFeedTapped(HealthAdViewState adViewState) {
            Log.d("Presenter", "onAdFeedTapped");
        }

        @Override
        public void onTipFeedTapped(HealthTipViewState adViewState) {
            Log.d("Presenter", "onTipFeedTapped");
        }
    };

    HealthFeedActivityPresenter(HealthFeedUseCase useCase, HealthFeedView view) {
        this.useCase = useCase;
        this.view = view;
    }

    void startPresenting() {
        disposable = useCase.healthFeed()
                .subscribe(new Consumer<HealthFeedViewState>() {
                    @Override
                    public void accept(HealthFeedViewState healthFeedViewState) throws Exception {
                        view.show(healthFeedViewState.feedViewStates());
                    }
                });
        view.setListener(listener);

    }

    void stopPresenting() {
        disposable.dispose();
    }
}
