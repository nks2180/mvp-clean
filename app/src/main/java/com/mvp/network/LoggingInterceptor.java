package com.mvp.network;

import android.util.Log;

import com.mvp.BuildConfig;
import com.mvp.common.Optional;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {

    private static final String TAG = BuildConfig.APPLICATION_ID;
    private static final String HEADERS_DIVIDER = "----------- Headers -----------%n";
    private static final String CACHE_DIVIDER = "--------- Cache stats ---------%n";
    private static final String NO_CACHE_DIVIDER = "----------- No cache ----------\n";
    private static final String PREFIX = "ID: %1$s %n.%n";
    private static final String SUFFIX = ".%n";
    private static final String REQUEST_TEMPLATE = PREFIX
            + "Request: %2$s %3$s %n"
            + HEADERS_DIVIDER
            + "%4$s %n"
            + SUFFIX;
    private static final String RESPONSE_TEMPLATE = PREFIX
            + "Response: %2$s %3$s %n"
            + "%4$s %n"
            + HEADERS_DIVIDER
            + "%5$s %n"
            + SUFFIX;
    private static final String CACHE_TEMPLATE = CACHE_DIVIDER + "requests=%1$s, network=%2$s, cache=%3$s";

    private final Optional<Cache> cache;

    public LoggingInterceptor(Optional<Cache> cache) {
        this.cache = cache;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        int id = request.hashCode();
        log(id, request);
        Response response = chain.proceed(request);
        log(id, response);
        return response;
    }

    private void log(int id, Request request) {
        Log.v(TAG, String.format(REQUEST_TEMPLATE, id, request.method(), request.url().toString(), request.headers()));
    }

    private void log(int id, Response response) {
        Log.v(TAG, String.format(RESPONSE_TEMPLATE, id, response.code(), response.message(), logCacheStats(), response.headers()));
    }

    private String logCacheStats() {
//        if (cache.isPresent()) {
//            Cache cacheInstance = this.cache.get();
//            return String.format(CACHE_TEMPLATE, cacheInstance.requestCount(),
//                    cacheInstance.networkCount(),
//                    cacheInstance.hitCount());
//        }
        return NO_CACHE_DIVIDER;
    }
}
