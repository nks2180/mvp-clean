package com.mvp.network;

import android.app.Application;

import com.mvp.BuildConfig;
import com.mvp.common.Optional;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    private static final int DEFAULT_DISK_CACHE_SIZE = 16 * 1024 * 1024; // 16MB
    private static final int PRIORITY_DISK_CACHE_SIZE = 8 * 1024 * 1024; // 8MB

    private final Cache defaultCache;

    public NetworkModule(Application application) {
        defaultCache = new Cache(createCache(application, "default_cache"), DEFAULT_DISK_CACHE_SIZE);
    }

    @Provides
    @Singleton
    public HttpUrl baseUrl() {
        return HttpUrl.parse("https://api.myjson.com/");
    }

    @Provides
    @Singleton
    public Retrofit retrofitWithDefaultCache(HttpUrl httpUrl) {
        return NetworkDefaults.retrofit()
                .newBuilder()
                .client(newOkHttpClientWith(defaultCache))
                .baseUrl(httpUrl)
                .build();
    }

    private File createCache(Application application, String default_cache) {
        return new File(application.getCacheDir(), default_cache);
    }

    private OkHttpClient newOkHttpClientWith(Cache cache) {
        OkHttpClient.Builder builder = NetworkDefaults.okHttpClient()
                .newBuilder()
                .cache(cache);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new LoggingInterceptor(Optional.of(cache)));
        }
        return builder.build();
    }
}
