package com.mvp.healthfeed.api;

import com.mvp.healthfeed.HealthFeed;
import com.squareup.moshi.Moshi;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class HealthFeedApiFetcher implements HealthFeedFetcher {

    private final FeedConverter converter;
    private final HealthBackend healthBackend;

    public static HealthFeedApiFetcher from(Retrofit retrofit,
                                            Moshi moshi) {
        FeedConverter converter = new FeedConverter();

        Moshi localMoshi = moshi.newBuilder()
                .add(ApiFeed.class, ApiFeedJsonAdapter.from(moshi))
                .build();

        HealthBackend healthBackend = retrofit
                .newBuilder()
                .addConverterFactory(MoshiConverterFactory.create(localMoshi))
                .build()
                .create(HealthBackend.class);
        return new HealthFeedApiFetcher(converter, healthBackend);
    }

    private HealthFeedApiFetcher(FeedConverter converter,
                                 HealthBackend healthBackend) {
        this.converter = converter;
        this.healthBackend = healthBackend;
    }

    @Override
    public Single<HealthFeed> load() {
        return healthBackend
                .load()
                .map(converter);
    }

}
