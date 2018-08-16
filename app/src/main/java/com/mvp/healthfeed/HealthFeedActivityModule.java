package com.mvp.healthfeed;

import com.mvp.common.AssetLoader;
import com.mvp.image.ImageLoader;
import com.mvp.rx.AndroidSchedulingStrategyFactory;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;

@Module()
public class HealthFeedActivityModule {

    @Provides
    HealthFeedFetcher healthFeedFetcher(Moshi moshi, AssetLoader assetLoader) {
        return HealthFeedFetcher.from(moshi, assetLoader);
    }

    @Provides
    HealthFeedUseCase useCase(HealthFeedFetcher healthFeedFetcher) {
        HealthFeedViewStateConverter viewStateConverter = new HealthFeedViewStateConverter();
        return new HealthFeedUseCase(healthFeedFetcher,
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
