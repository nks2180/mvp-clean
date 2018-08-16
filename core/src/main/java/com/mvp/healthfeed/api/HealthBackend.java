package com.mvp.healthfeed.api;

import com.mvp.healthfeed.api.ApiHealthFeed;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

interface HealthBackend {
    String URL = "bins/ov00c";

    @GET(URL)
    Single<ApiHealthFeed> load();
}
