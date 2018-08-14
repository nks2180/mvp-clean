package com.mvp.common;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AnalyticsModule {

    @Provides
    @Singleton
    public AnalyticsReporter analyticsReporter(Application application) {
        return AnalyticsReporter.DUMMY;
    }
}
