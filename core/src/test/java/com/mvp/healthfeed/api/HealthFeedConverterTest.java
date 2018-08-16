package com.mvp.healthfeed.api;

import com.mvp.common.Optional;
import com.mvp.healthfeed.HealthAdFeed;
import com.mvp.healthfeed.HealthFeed;
import com.mvp.healthfeed.HealthQnaFeed;
import com.mvp.healthfeed.HealthQuizFeed;
import com.mvp.healthfeed.HealthTipFeed;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HealthFeedConverterTest {
    public static final ApiStatus API_STATUS = new ApiStatus(11, "message");
    public static final ApiMedia API_MEDIA = new ApiMedia("http://media.path", "image");
    public static final ApiFeed API_TIP_FEED = new ApiTipFeed(100, "Tip title", "Tip body", "tag", "Tip support text", Arrays.asList(API_MEDIA));
    public static final ApiFeed API_QNA_FEED = new ApiQnAFeed(200, "Qna Title", "Qna body", "tag", "Qna support text", Arrays.asList(API_MEDIA));
    public static final ApiFeed API_AD_FEED = new ApiAdFeed(300, "Ad title", "Ad body", "Ad tag", "Ad support text", Arrays.asList(API_MEDIA));
    public static final ApiFeed API_QUIZ_FEED = new ApiQuizFeed(500, "Quiz title", "Quiz body", "Quiz tag", "Quiz support text", Arrays.asList(API_MEDIA));

    private HealthFeedConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new HealthFeedConverter();
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

        HealthTipFeed expectedTipFeed = new HealthTipFeed(100, "Tip title", "Tip body", "tag", "Tip support text", Optional.of("http://media.path"));
        assertThat(convertedHealthFeed.healthStories.get(0)).isEqualTo(expectedTipFeed);

    }

    @Test
    public void shouldConvertApiQnaFeedToCorrectTipFeed() throws Exception {
        ApiHealthFeed apiHealthFeed = new ApiHealthFeedBuilder()
                .withFeeds(API_QNA_FEED)
                .build();

        HealthFeed convertedHealthFeed = converter.apply(apiHealthFeed);

        HealthQnaFeed expectedQnAFeed = new HealthQnaFeed(200, "Qna Title", "Qna body", "tag", "Qna support text", Optional.of("http://media.path"));
        assertThat(convertedHealthFeed.healthStories.get(0)).isEqualTo(expectedQnAFeed);

    }

    @Test
    public void shouldConvertApiAdFeedToCorrectAdFeed() throws Exception {
        ApiHealthFeed apiHealthFeed = new ApiHealthFeedBuilder()
                .withFeeds(API_AD_FEED)
                .build();

        HealthFeed convertedHealthFeed = converter.apply(apiHealthFeed);

        HealthAdFeed expectedAdFeed = new HealthAdFeed(300, "Ad title", "Ad body", "Ad tag", "Ad support text", Optional.of("http://media.path"));
        assertThat(convertedHealthFeed.healthStories.get(0)).isEqualTo(expectedAdFeed);

    }

    @Test
    public void shouldConvertApiQuizFeedToCorrectQuizFeed() throws Exception {
        ApiHealthFeed apiHealthFeed = new ApiHealthFeedBuilder()
                .withFeeds(API_QUIZ_FEED)
                .build();

        HealthFeed convertedHealthFeed = converter.apply(apiHealthFeed);

        HealthQuizFeed expectedQuizFeed = new HealthQuizFeed(500, "Quiz title", "Quiz body", "Quiz tag", "Quiz support text", Optional.of("http://media.path"));
        assertThat(convertedHealthFeed.healthStories.get(0)).isEqualTo(expectedQuizFeed);

    }
}
