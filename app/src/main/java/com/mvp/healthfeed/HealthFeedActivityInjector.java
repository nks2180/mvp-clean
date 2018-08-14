package com.mvp.healthfeed;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {HealthFeedActivityModule.class})
public interface HealthFeedActivityInjector extends AndroidInjector<HealthFeedActivity> {

    void inject(HealthFeedActivity activity);

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HealthFeedActivity> {

        public abstract HealthFeedActivityInjector build();
    }
}
