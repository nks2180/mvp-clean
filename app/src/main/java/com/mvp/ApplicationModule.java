package com.mvp;

import android.app.Application;

import com.mvp.common.AnalyticsModule;
import com.mvp.common.AndroidAssetLoader;
import com.mvp.common.AssetLoader;
import com.mvp.common.TimeZoneModule;
import com.mvp.image.ImageLoaderModule;
import com.mvp.network.JsonModule;
import com.mvp.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = {
        JsonModule.class,
        TimeZoneModule.class,
        NetworkModule.class,
        ImageLoaderModule.class,
        AnalyticsModule.class})
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }


    @Provides
    @Singleton
    Application application() {
        return application;
    }


    @Provides
    @Singleton
    AssetLoader assetLoader() {
        return new AndroidAssetLoader(application);
    }

}
