package com.mvp.healthfeed.api;

import com.mvp.common.AssetLoader;
import com.mvp.healthfeed.HealthFeed;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;

import java.io.InputStream;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import okio.Buffer;

public class HealthFeedLocalFetcher implements HealthFeedFetcher {
    private static final String HEALTH_FEED_JSON = "healthfeed.json";

    private final AssetLoader assetLoader;
    private final HealthFeedConverter converter;
    private final JsonAdapter<ApiHealthFeed> adapter;

    public static HealthFeedLocalFetcher from(Moshi moshi,
                                              AssetLoader assetLoader) {
        HealthFeedConverter converter = new HealthFeedConverter();
        JsonAdapter<ApiHealthFeed> adapter = moshi.newBuilder()
                .add(ApiFeed.class, ApiFeedJsonAdapter.from(moshi))
                .build()
                .adapter(ApiHealthFeed.class);
        return new HealthFeedLocalFetcher(converter, assetLoader, adapter);
    }

    private HealthFeedLocalFetcher(HealthFeedConverter converter,
                                   AssetLoader assetLoader,
                                   JsonAdapter<ApiHealthFeed> adapter) {
        this.converter = converter;
        this.assetLoader = assetLoader;
        this.adapter = adapter;
    }

    @Override
    public Single<HealthFeed> load() {
        return Single.fromCallable(readHealthFeedFrom(HEALTH_FEED_JSON))
                .map(converter);
    }

    private Callable<ApiHealthFeed> readHealthFeedFrom(final String assetUrl) {
        return new Callable<ApiHealthFeed>() {
            @Override
            public ApiHealthFeed call() throws Exception {
                InputStream inputStream = assetLoader.loadAsset(assetUrl);
                Buffer buffer = new Buffer();
                JsonReader jsonReader = JsonReader.of(buffer.readFrom(inputStream));
                ApiHealthFeed apiHealthFeed = adapter.fromJson(jsonReader);
                jsonReader.close();
                buffer.close();
                return apiHealthFeed;
            }
        };
    }

}
