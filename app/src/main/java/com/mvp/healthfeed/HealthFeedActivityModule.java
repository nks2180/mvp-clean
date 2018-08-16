package com.mvp.healthfeed;

import com.mvp.common.AssetLoader;
import com.mvp.healthfeed.api.HealthFeedApiFetcher;
import com.mvp.healthfeed.api.HealthFeedLocalFetcher;
import com.mvp.image.ImageLoader;
import com.mvp.rx.AndroidSchedulingStrategyFactory;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module()
public class HealthFeedActivityModule {

    @Provides
    HealthFeedLocalFetcher localHealthFeedFetcher(Moshi moshi, AssetLoader assetLoader) {
        return HealthFeedLocalFetcher.from(moshi, assetLoader);
    }

    @Provides
    HealthFeedApiFetcher apiHealthFeedFetcher(Retrofit retrofit, Moshi moshi) {
        return HealthFeedApiFetcher.from(retrofit, moshi);
    }

    @Provides
    HealthFeedUseCase useCase(HealthFeedApiFetcher fetcher) {
        HealthFeedViewStateConverter viewStateConverter = new HealthFeedViewStateConverter();
        return new HealthFeedUseCase(fetcher,
                viewStateConverter,
                AndroidSchedulingStrategyFactory.io());
    }

    @Provides
    HealthFeedActivityPresenter presenter(HealthFeedActivity activity,
                                          HealthFeedUseCase useCase,
                                          ImageLoader imageLoader) {
        HealthFeedView feedView = HealthFeedView.from(activity, imageLoader);
        return new HealthFeedActivityPresenter(useCase, feedView);
    }
}
