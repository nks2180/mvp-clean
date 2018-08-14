package com.mvp.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AndroidSchedulingStrategyFactory extends SchedulingStrategy.Factory {

    public static AndroidSchedulingStrategyFactory newThread() {
        return new AndroidSchedulingStrategyFactory(Schedulers.newThread());
    }

    public static AndroidSchedulingStrategyFactory io() {
        return new AndroidSchedulingStrategyFactory(Schedulers.io());
    }

    private AndroidSchedulingStrategyFactory(Scheduler subscribingScheduler) {
        super(subscribingScheduler, AndroidSchedulers.mainThread());
    }
}
