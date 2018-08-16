package com.mvp.image;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class ImageLoaderModule {

    @Provides
    @Singleton
    ImageLoader imageLoader(Application application) {
        return new PicassoImageLoader();
    }
}
