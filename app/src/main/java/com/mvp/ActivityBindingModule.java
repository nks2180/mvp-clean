package com.mvp;

import android.app.Activity;

import com.mvp.healthfeed.HealthFeedActivity;
import com.mvp.healthfeed.HealthFeedActivityInjector;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        HealthFeedActivityInjector.class})
public interface ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(HealthFeedActivity.class)
    AndroidInjector.Factory<? extends Activity> bindMainActivity(HealthFeedActivityInjector.Builder builder);
}
