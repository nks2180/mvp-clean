package com.mvp.healthfeed;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

class HealthFeedActivityPresenter {

    private final HealthFeedUseCase useCase;
    private final HealthFeedView view;
    private Disposable disposable = Disposables.empty();

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

    }

     void stopPresenting() {
        disposable.dispose();
    }
}
