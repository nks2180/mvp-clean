package com.mvp.healthfeed;

import com.mvp.common.Optional;
import com.mvp.healthfeed.api.ApiAdFeed;
import com.mvp.healthfeed.api.ApiFeed;
import com.mvp.healthfeed.api.ApiHealthFeed;
import com.mvp.healthfeed.api.ApiMedia;
import com.mvp.healthfeed.api.ApiQnAFeed;
import com.mvp.healthfeed.api.ApiQuizFeed;
import com.mvp.healthfeed.api.ApiTipFeed;
import com.mvp.rx.Converter;

import java.util.ArrayList;
import java.util.List;


public class HealthFeedConverter implements Converter<ApiHealthFeed, HealthFeed>, ApiFeed.Visitor {


    @Override
    public HealthFeed apply(ApiHealthFeed apiHealthFeed) throws Exception {
        List<ApiFeed> apiFeeds = apiHealthFeed.healthStories;
        List<Feed> feeds = new ArrayList<>();
        for (ApiFeed apiFeed : apiFeeds) {
            Feed feed = apiFeed.accept(this);
            feeds.add(feed);
        }
        return new HealthFeed(feeds);
    }

    @Override
    public Feed visit(ApiTipFeed apiTipFeed) {
        List<Media> mediaList = new ArrayList<>(apiTipFeed.mediaList.size());
        for (ApiMedia apiMedia : apiTipFeed.mediaList) {
            mediaList.add(new Media(apiMedia.path, apiMedia.type));

        }
        return new HealthTipFeed(apiTipFeed.code,
                apiTipFeed.title,
                apiTipFeed.body,
                apiTipFeed.tag,
                apiTipFeed.supportText,
                imagaPathfrom(mediaList));
    }

    @Override
    public Feed visit(ApiQnAFeed apiQnAFeed) {
        List<Media> mediaList = new ArrayList<>(apiQnAFeed.mediaList.size());
        for (ApiMedia apiMedia : apiQnAFeed.mediaList) {
            mediaList.add(new Media(apiMedia.path, apiMedia.type));
        }
        return new HealthQnaFeed(apiQnAFeed.code,
                apiQnAFeed.title,
                apiQnAFeed.body,
                apiQnAFeed.tag,
                apiQnAFeed.supportText,
                imagaPathfrom(mediaList));
    }

    @Override
    public Feed visit(ApiQuizFeed apiQuizFeed) {
        List<Media> mediaList = new ArrayList<>(apiQuizFeed.mediaList.size());
        for (ApiMedia apiMedia : apiQuizFeed.mediaList) {
            mediaList.add(new Media(apiMedia.path, apiMedia.type));
        }
        return new HealthQuizFeed(apiQuizFeed.code,
                apiQuizFeed.title,
                apiQuizFeed.body,
                apiQuizFeed.tag,
                apiQuizFeed.supportText,
                imagaPathfrom(mediaList));
    }

    @Override
    public Feed visit(ApiAdFeed apiAdFeed) {
        List<Media> mediaList = new ArrayList<>(apiAdFeed.mediaList.size());
        for (ApiMedia apiMedia : apiAdFeed.mediaList) {
            mediaList.add(new Media(apiMedia.path, apiMedia.type));
        }
        return new HealthAdFeed(apiAdFeed.code,
                apiAdFeed.title,
                apiAdFeed.body,
                apiAdFeed.tag,
                apiAdFeed.supportText,
                imagaPathfrom(mediaList));
    }

    private Optional<String> imagaPathfrom(List<Media> media) {
        if (media != null && media.size() > 0 && media.get(0).path != null) {
            return Optional.of(media.get(0).path);
        }
        return Optional.absent();
    }
}
