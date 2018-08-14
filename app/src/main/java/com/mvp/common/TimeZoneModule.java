package com.mvp.common;

import java.util.TimeZone;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class TimeZoneModule {
    @Provides
    @Singleton
    public TimeZone timeZone() {
        return TimeZone.getDefault();
    }
}
