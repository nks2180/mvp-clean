package com.mvp.network;

import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class JsonModule {

    @Provides
    @Singleton
    public Moshi moshi() {
        return JsonDefaults.moshi();
    }

}
