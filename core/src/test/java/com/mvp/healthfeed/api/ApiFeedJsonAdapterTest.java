package com.mvp.healthfeed.api;

import com.mvp.network.JsonDefaults;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;
import static com.mvp.test.TestResources.jsonReaderFrom;

public class ApiFeedJsonAdapterTest {

    private ApiFeedJsonAdapter feedJsonAdapter;

    @Before
    public void setUp() throws Exception {
        feedJsonAdapter = ApiFeedJsonAdapter.from(JsonDefaults.moshi());
    }

    @Test
    public void shouldParseTipCorrectly() throws Exception {
        ApiTipFeed tipFeed = (ApiTipFeed) feedJsonAdapter.fromJson(jsonReaderFrom("tip_feed.json"));

        assertThat(tipFeed.code).isEqualTo(16);
        assertThat(tipFeed.title).isEqualTo("Neurobics for your mind.");
        assertThat(tipFeed.body).isEqualTo("Get your brain fizzing with energy. American researchers coined the term ‘neurobics’ for tasks which activate the brain's own biochemical pathways and to bring new pathways online that can help to strengthen or preserve brain circuits.");
        assertThat(tipFeed.supportText).isEqualTo("stretching exercises");
        assertThat(tipFeed.tag).isEqualTo("Stretching");
        assertThat(tipFeed.mediaList.get(0).path).isEqualTo("http://www.antiqueprints.com/images/ai0/i0772.jpg");
        assertThat(tipFeed.mediaList.get(0).type).isEqualTo("image");
    }

    @Test
    public void shouldParseQnACorrectly() throws Exception {
        ApiQnAFeed qnAFeed = (ApiQnAFeed) feedJsonAdapter.fromJson(jsonReaderFrom("qna_feed.json"));

        assertThat(qnAFeed.code).isEqualTo(14);
        assertThat(qnAFeed.title).isEqualTo("Will a person with Type 2 diabetes under control end up with the need for insulin?");
        assertThat(qnAFeed.body).isEqualTo("As you may have read, Type 2 diabetes is a progressive disease.");
        assertThat(qnAFeed.mediaList.get(0).path).isEqualTo("http://www.ancestryimages.com/stockimages/sm0118-York-plan(g387)-l.jpg");
        assertThat(qnAFeed.mediaList.get(0).type).isEqualTo("image");
    }

    @Test
    public void shouldParseQuizCorrectly() throws Exception {
        ApiQuizFeed quizFeed = (ApiQuizFeed) feedJsonAdapter.fromJson(jsonReaderFrom("quiz_feed.json"));

        assertThat(quizFeed.code).isEqualTo(13);
        assertThat(quizFeed.title).isEqualTo("Take current affairs quizzes");
        assertThat(quizFeed.body).isEqualTo("Now a days, there is a separate section");
        assertThat(quizFeed.supportText).isEqualTo("Stretching exercises Quiz");
        assertThat(quizFeed.tag).isEqualTo("Stretching Quiz");
        assertThat(quizFeed.mediaList.get(0).path).isEqualTo("http://www.antiqueprints.com/images/ai0/i0773.jpg");
        assertThat(quizFeed.mediaList.get(0).type).isEqualTo("image");
    }

    @Test
    public void shouldParseAdCorrectly() throws Exception {
        ApiAdFeed adFeed = (ApiAdFeed) feedJsonAdapter.fromJson(jsonReaderFrom("ad_feed.json"));

        assertThat(adFeed.code).isEqualTo(15);
        assertThat(adFeed.title).isEqualTo("Get Health Tests Done from Comfort of your Home");
        assertThat(adFeed.body).isEqualTo("It is well known that ‘prevention is better than cure.");
        assertThat(adFeed.supportText).isEqualTo("Limited time offer. Avail Now!");
        assertThat(adFeed.tag).isEqualTo("Full Body");
        assertThat(adFeed.mediaList.get(0).path).isEqualTo("http://www.ancestryimages.com/stockimages/sm0112-Essex-Moule-l.jpg");
        assertThat(adFeed.mediaList.get(0).type).isEqualTo("image");
    }
}
