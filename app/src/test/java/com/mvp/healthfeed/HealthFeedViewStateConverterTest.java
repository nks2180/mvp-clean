package com.mvp.healthfeed;

import android.view.View;

import com.mvp.common.Optional;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

public class HealthFeedViewStateConverterTest {
    private static final String INVALID_IMAGE_URL = "http://image.url";
    private static final Feed TIP_FEED = HealthTipFeed.create(100, "Tip title", "Tip body", "tag", "Tip support text", Optional.of("http://media.path"));
    private static final Feed QNA_FEED = HealthQnaFeed.create(200, "Qna Title", "Qna body", "tag", "Qna support text", Optional.of("http://media.path"));
    private static final Feed QUIZ_FEED = HealthQuizFeed.create(500, "Quiz title", "Quiz body", "Quiz tag", "Quiz support text", Optional.of("http://media.path"));
    private static final Feed AD_FFED = HealthAdFeed.create(300, "Ad title", "Ad body", "Ad tag", "Ad support text", Optional.of("http://media.path"));

    private HealthFeedViewStateConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new HealthFeedViewStateConverter();
    }

    @Test
    public void shouldConvertTipFeedToTipFeedViewState() throws Exception {
        HealthFeed feed = healthFeedWith(TIP_FEED);

        HealthFeedViewState convertedViewState =  converter.apply(feed);

        assertThat(convertedViewState.feedViewStates()).contains(HealthTipViewState.create(100, "Tip title" ,"Tip body", "tag", "Tip support text", View.VISIBLE, "http://media.path"));
    }

    @Test
    public void shouldConvertQnaFeedToQnAFeedViewState() throws Exception {
        HealthFeed feed = healthFeedWith(QNA_FEED);

        HealthFeedViewState convertedViewState =  converter.apply(feed);

        assertThat(convertedViewState.feedViewStates()).contains(HealthQnaViewState.create(200, "Qna Title", "Qna body", View.VISIBLE, "http://media.path"));
    }

    @Test
    public void shouldConvertQuizFeedToQuizFeedViewState() throws Exception {
        HealthFeed feed = healthFeedWith(QUIZ_FEED);

        HealthFeedViewState convertedViewState =  converter.apply(feed);

        assertThat(convertedViewState.feedViewStates()).contains(HealthQuizViewState.create(500, "Quiz title", "Quiz body", "Quiz tag", "Quiz support text", View.VISIBLE, "http://media.path"));
    }

    @Test
    public void shouldConvertAdFeedToadFeedViewState() throws Exception {
        HealthFeed feed = healthFeedWith(AD_FFED);

        HealthFeedViewState convertedViewState =  converter.apply(feed);

        assertThat(convertedViewState.feedViewStates()).contains(HealthAdViewState.create(300, "Ad title", "Ad body", "Ad tag", "Ad support text", View.VISIBLE, "http://media.path"));
    }

    @Test
    public void shouldReturnBannerVisibilityAsFalseIfBannerImageUrlIsAbsent() throws Exception {
        Feed feed = HealthAdFeed.create(300, "Ad title", "Ad body", "Ad tag", "Ad support text", Optional.<String>absent());
        HealthFeed healthFeed = healthFeedWith(feed);

        HealthFeedViewState convertedViewState =  converter.apply(healthFeed);

        assertThat(convertedViewState.feedViewStates()).contains(HealthAdViewState.create(300, "Ad title", "Ad body", "Ad tag", "Ad support text", View.GONE, INVALID_IMAGE_URL));
    }

    private HealthFeed healthFeedWith(Feed... feeds) {
        return new HealthFeed(Arrays.asList(feeds));
    }
}
