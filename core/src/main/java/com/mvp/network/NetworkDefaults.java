package com.mvp.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public final class NetworkDefaults {

    private static final int DEFAULT_TIMEOUT = 30;

    private NetworkDefaults() {
        throw new UnsupportedOperationException("No instance allowed!");
    }

    static OkHttpClient okHttpClient() {
        return OkHttpClientHolder.INSTANCE.getOkHttpClient();
    }

    static Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .client(okHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private enum OkHttpClientHolder {
        INSTANCE;

        private final OkHttpClient okHttpClient;

        OkHttpClientHolder() {
            okHttpClient = defaultOkHttpClient();
        }

        private OkHttpClient defaultOkHttpClient() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            return builder.build();
        }

        public OkHttpClient getOkHttpClient() {
            return okHttpClient;
        }
    }
}
