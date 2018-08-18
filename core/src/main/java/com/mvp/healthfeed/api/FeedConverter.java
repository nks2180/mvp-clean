package com.mvp.healthfeed.api;

import com.mvp.common.Optional;
import com.mvp.healthfeed.Feed;
import com.mvp.healthfeed.AdFeed;
import com.mvp.healthfeed.HealthFeed;
import com.mvp.healthfeed.QnaFeed;
import com.mvp.healthfeed.QuizFeed;
import com.mvp.healthfeed.TipFeed;
import com.mvp.healthfeed.Media;
import com.mvp.rx.Converter;

import java.util.ArrayList;
import java.util.List;

public class FeedConverter implements Converter<ApiHealthFeed, HealthFeed>, ApiFeed.Visitor {


    @Override
    public HealthFeed apply(ApiHealthFeed apiHealthFeed) throws Exception {

        List<Feed> feeds = new ArrayList<>();
        if (apiHealthFeed.status.code == 200) {
            List<ApiFeed> apiFeeds = apiHealthFeed.healthStories;
            for (ApiFeed apiFeed : apiFeeds) {
                Feed feed = apiFeed.accept(this);
                feeds.add(feed);
            }
        }
        return new HealthFeed(feeds);
    }

    @Override
    public Feed visit(ApiTipFeed apiTipFeed) {
        List<Media> mediaList = new ArrayList<>(apiTipFeed.mediaList.size());
        for (ApiMedia apiMedia : apiTipFeed.mediaList) {
            mediaList.add(new Media(apiMedia.path, apiMedia.type));

        }
        return TipFeed.create(apiTipFeed.code,
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
        return QnaFeed.create(apiQnAFeed.code,
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
        return QuizFeed.create(apiQuizFeed.code,
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
        return AdFeed.create(apiAdFeed.code,
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
