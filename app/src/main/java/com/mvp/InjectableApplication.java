package com.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;

public abstract class InjectableApplication extends Application implements HasDispatchingActivityInjector {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    private final ApplicationInjector.Factory applicationInjectorFactory;

    protected InjectableApplication(ApplicationInjector.Factory applicationInjectorFactory) {
        this.applicationInjectorFactory = applicationInjectorFactory;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector applicationInjector = applicationInjectorFactory.create(this);
        applicationInjector.inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
