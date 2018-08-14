package com.mvp.rx;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;

/**
 * This class can be used in an Observable pipeline to provide the schedulers for subscribing and observing
 * the emissions. You should always use a {@link Factory} to create one when needed. Eg:
 * <pre>
 *
 *     Observable&lt;String&gt; source = ...
 *     source.compose(factory.&lt;String&gt;create());
 *
 * </pre>
 */
public final class SchedulingStrategy<T> implements ObservableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T>,
        CompletableTransformer {

    private final Scheduler subscribingScheduler;
    private final Scheduler observingScheduler;

    SchedulingStrategy(Scheduler subscribingScheduler, Scheduler observingScheduler) {
        this.subscribingScheduler = subscribingScheduler;
        this.observingScheduler = observingScheduler;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(subscribingScheduler)
                .observeOn(observingScheduler);
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .subscribeOn(subscribingScheduler)
                .observeOn(observingScheduler);
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream
                .subscribeOn(subscribingScheduler)
                .observeOn(observingScheduler);
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream
                .subscribeOn(subscribingScheduler)
                .observeOn(observingScheduler);
    }

    public static class Factory {

        private final Scheduler subscribingScheduler;
        private final Scheduler observingScheduler;

        public Factory(Scheduler subscribingScheduler, Scheduler observingScheduler) {
            this.subscribingScheduler = subscribingScheduler;
            this.observingScheduler = observingScheduler;
        }

        public <T> SchedulingStrategy<T> create() {
            return new SchedulingStrategy<>(subscribingScheduler, observingScheduler);
        }

        public Scheduler getSubscribingScheduler() {
            return subscribingScheduler;
        }

        public Scheduler getObservingScheduler() {
            return observingScheduler;
        }
    }
}
