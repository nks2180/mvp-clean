package com.mvp.healthfeed.api;

import com.mvp.common.Optional;
import com.mvp.healthfeed.AdFeed;
import com.mvp.healthfeed.HealthFeed;
import com.mvp.healthfeed.QnaFeed;
import com.mvp.healthfeed.QuizFeed;
import com.mvp.healthfeed.TipFeed;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

public class FeedConverterTest {
    private static final ApiStatus API_STATUS = new ApiStatus(11, "message");
    private static final ApiMedia API_MEDIA = new ApiMedia("http://media.path", "image");
    private static final ApiFeed API_TIP_FEED = new ApiTipFeed(100, "Tip title", "Tip body", "tag", "Tip support text", Arrays.asList(API_MEDIA));
    private static final ApiFeed API_QNA_FEED = new ApiQnAFeed(200, "Qna Title", "Qna body", "tag", "Qna support text", Arrays.asList(API_MEDIA));
    private static final ApiFeed API_AD_FEED = new ApiAdFeed(300, "Ad title", "Ad body", "Ad tag", "Ad support text", Arrays.asList(API_MEDIA));
    private static final ApiFeed API_QUIZ_FEED = new ApiQuizFeed(500, "Quiz title", "Quiz body", "Quiz tag", "Quiz support text", Arrays.asList(API_MEDIA));

    private FeedConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new FeedConverter();
    }

    @Test
    public void shouldReturnEmptyHealthFeedIfStatusIsNotTwoHundred() throws Exception {
        ApiHealthFeed apiHealthFeed = new ApiHealthFeedBuilder()
                .withStatus(API_STATUS)
                .withFeeds(API_TIP_FEED, API_QNA_FEED, API_TIP_FEED)
                .build();
        HealthFeed convertedHealthFeed = converter.apply(apiHealthFeed);

        assertThat(convertedHealthFeed.healthStories).isEqualTo(Collections.emptyList());

    }

    @Test
    public void shouldConvertApiTipFeedToCorrectTipFeed() throws Exception {
        ApiHealthFeed apiHealthFeed = new ApiHealthFeedBuilder()
                .withFeeds(API_TIP_FEED)
                .build();

        HealthFeed convertedHealthFeed = converter.apply(apiHealthFeed);

        TipFeed expectedTipFeed = TipFeed.create(100, "Tip title", "Tip body", "tag", "Tip support text", Optional.of("http://media.path"));
        assertThat(convertedHealthFeed.healthStories.get(0)).isEqualTo(expectedTipFeed);

    }

    @Test
    public void shouldConvertApiQnaFeedToCorrectTipFeed() throws Exception {
        ApiHealthFeed apiHealthFeed = new ApiHealthFeedBuilder()
                .withFeeds(API_QNA_FEED)
                .build();

        HealthFeed convertedHealthFeed = converter.apply(apiHealthFeed);
        QnaFeed expectedQnAFeed = QnaFeed.create(200, "Qna Title", "Qna body", "tag", "Qna support text", Optional.of("http://media.path"));
        assertThat(convertedHealthFeed.healthStories.get(0)).isEqualTo(expectedQnAFeed);
    }

    @Test
    public void shouldConvertApiAdFeedToCorrectAdFeed() throws Exception {
        ApiHealthFeed apiHealthFeed = new ApiHealthFeedBuilder()
                .withFeeds(API_AD_FEED)
                .build();

        HealthFeed convertedHealthFeed = converter.apply(apiHealthFeed);

        AdFeed expectedAdFeed = AdFeed.create(300, "Ad title", "Ad body", "Ad tag", "Ad support text", Optional.of("http://media.path"));
        assertThat(convertedHealthFeed.healthStories.get(0)).isEqualTo(expectedAdFeed);

    }

    @Test
    public void shouldConvertApiQuizFeedToCorrectQuizFeed() throws Exception {
        ApiHealthFeed apiHealthFeed = new ApiHealthFeedBuilder()
                .withFeeds(API_QUIZ_FEED)
                .build();

        HealthFeed convertedHealthFeed = converter.apply(apiHealthFeed);

        QuizFeed expectedQuizFeed = QuizFeed.create(500, "Quiz title", "Quiz body", "Quiz tag", "Quiz support text", Optional.of("http://media.path"));
        assertThat(convertedHealthFeed.healthStories.get(0)).isEqualTo(expectedQuizFeed);

    }
}
