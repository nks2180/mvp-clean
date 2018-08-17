package com.mvp.healthfeed;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import io.reactivex.Single;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HealthFeedActivityPresenterTest {
    
    private static final HealthFeedViewState HEALTH_FEED_VIEW_STATE = HealthFeedViewState.create(Collections.<FeedViewState>emptyList());

    @Mock private HealthFeedView view;
    @Mock private HealthFeedUseCase useCase;

    private HealthFeedActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        when(useCase.healthFeed()).thenReturn(Single.just(HEALTH_FEED_VIEW_STATE));
        presenter = new HealthFeedActivityPresenter(useCase, view);
    }

    @Test
    public void ShouldUpdateViewWhenHealthFeedResponseCome() throws Exception {
        presenter.startPresenting();

        verify(view).show(HEALTH_FEED_VIEW_STATE.feedViewStates());
    }
}
