package com.mvp;

import android.app.Application;

import com.mvp.network.NetworkModule;

public class MvpApplication extends InjectableApplication {

    public MvpApplication() {
        super(new ApplicationInjectorFactory());
    }

    public static class ApplicationInjectorFactory implements ApplicationInjector.Factory {

        @Override
        public ApplicationInjector create(Application application) {
            return DaggerApplicationInjector.builder()
                    .applicationModule(new ApplicationModule(application))
                    .networkModule(new NetworkModule(application))
                    .build();
        }
    }
}
