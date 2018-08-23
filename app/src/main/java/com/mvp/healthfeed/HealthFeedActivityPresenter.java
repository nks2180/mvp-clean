package com.mvp.healthfeed;

import com.mvp.Navigation.Navigator;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;

class HealthFeedActivityPresenter {

    private final HealthFeedUseCase useCase;
    private final HealthFeedDisplayer displayer;
    private final Navigator navigator;
    private Disposable disposable = Disposables.empty();
    private final HealthFeedView.Listener listener = new HealthFeedView.Listener() {

        @Override
        public void onQuizFeedTapped(HealthQuizViewState adViewState) {
            navigator.toQuizDetail();
        }

        @Override
        public void onQnAFeedTapped(HealthQnaViewState adViewState) {
            navigator.toQnaDetail();
        }

        @Override
        public void onAdFeedTapped(HealthAdViewState adViewState) {
            navigator.toAdDetail();
        }

        @Override
        public void onTipFeedTapped(HealthTipViewState adViewState) {
            navigator.toTipDetail();
        }
    };

    HealthFeedActivityPresenter(HealthFeedUseCase useCase, HealthFeedDisplayer displayer, Navigator navigator) {
        this.useCase = useCase;
        this.displayer = displayer;
        this.navigator = navigator;
    }

    void startPresenting() {
        disposable = useCase.healthFeed()
                .subscribe(new Consumer<HealthFeedViewState>() {
                    @Override
                    public void accept(HealthFeedViewState healthFeedViewState) throws Exception {
                        displayer.show(healthFeedViewState);
                    }
                });
        displayer.setListener(listener);

    }

    void stopPresenting() {
        disposable.dispose();
        displayer.setListener(HealthFeedView.Listener.NO_OP);
    }
}
