package com.mvp;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class})
interface ApplicationInjector {

    void inject(InjectableApplication application);

    interface Factory {

        ApplicationInjector create(Application application);
    }
}

