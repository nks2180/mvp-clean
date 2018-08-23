package com.mvp.healthfeed;

import com.mvp.healthfeed.HealthFeedErrorViewState.Type;
import com.squareup.moshi.JsonEncodingException;

import java.io.IOException;
import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

class HealthFeedViewStateErrorConverter implements ObservableTransformer<HealthFeedViewState, HealthFeedViewState> {

    private static final int HTTP_SERVER_ERROR_CODE = 500;

    @Override
    public ObservableSource<HealthFeedViewState> apply(Observable<HealthFeedViewState> upstream) {
        return upstream
                .onErrorResumeNext(new Function<Throwable, ObservableSource<HealthFeedViewState>>() {
                    @Override
                    public ObservableSource<HealthFeedViewState> apply(Throwable cause) throws Exception {
                        return Observable.just(convertToError(cause));
                    }
                });
    }

    private HealthFeedViewState convertToError(Throwable cause) {
        if (cause instanceof HttpException) {
            HttpException httpException = (HttpException) cause;
            if (httpException.code() == HTTP_SERVER_ERROR_CODE) {
                return HealthFeedErrorViewState.createError(cause, Type.SERVER);
            }
        }
        if (cause instanceof JsonEncodingException) {
            return HealthFeedErrorViewState.createError(cause, Type.UNKNOWN);
        }
        if (cause instanceof IOException) {
            return HealthFeedErrorViewState.createError(cause, Type.CONNECTION);
        }
        return HealthFeedErrorViewState.createError(cause, Type.UNKNOWN);
    }


}
