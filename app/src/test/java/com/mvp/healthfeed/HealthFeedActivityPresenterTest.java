package com.mvp.healthfeed;

import com.mvp.Navigation.Navigator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import io.reactivex.Single;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HealthFeedActivityPresenterTest {

    private static final HealthFeedViewState HEALTH_FEED_VIEW_STATE = HealthFeedViewState.create(Collections.<FeedViewState>emptyList());

    @Mock private HealthFeedView view;
    @Mock private HealthFeedUseCase useCase;
    @Mock private Navigator navigator;
    @Mock private HealthQnaViewState QNA_VIEW_STATE;
    @Mock private HealthQuizViewState QUIZ_VIEW_STATE;
    @Mock private HealthTipViewState TIP_VIEW_STATE;
    @Mock private HealthAdViewState AD_VIEW_STATE;

    private HealthFeedActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        when(useCase.healthFeed()).thenReturn(Single.just(HEALTH_FEED_VIEW_STATE));
        presenter = new HealthFeedActivityPresenter(useCase, view, navigator);
    }

    @Test
    public void shouldUpdateViewWhenHealthFeedResponseCome() throws Exception {
        presenter.startPresenting();

        verify(view).show(HEALTH_FEED_VIEW_STATE.feedViewStates());
    }

    @Test
    public void shouldStopPresentingWhenStopPresentingIsCalled() throws Exception {
        presenter.startPresenting();

        presenter.stopPresenting();

        verify(view).setListener(HealthFeedView.Listener.NO_OP);
    }

    @Test
    public void shouldNavigateToQnaDetailWhenQnaFeedTapped() {
        presenter.startPresenting();

        invoke().onQnAFeedTapped(QNA_VIEW_STATE);

        verify(navigator).toQnaDetail();
    }

    @Test
    public void shouldNavigateToQuizDetailWhenQuizFeedTapped() {
        presenter.startPresenting();

        invoke().onQuizFeedTapped(QUIZ_VIEW_STATE);

        verify(navigator).toQuizDetail();
    }

    @Test
    public void shouldNavigateToTipDetailWhenTipFeedTapped() {
        presenter.startPresenting();

        invoke().onTipFeedTapped(TIP_VIEW_STATE);

        verify(navigator).toTipDetail();
    }

    @Test
    public void shouldNavigateToAdDetailWhenAdFeedTapped() {
        presenter.startPresenting();

        invoke().onAdFeedTapped(AD_VIEW_STATE);

        verify(navigator).toAdDetail();
    }

    private HealthFeedView.Listener invoke() {
        ArgumentCaptor<HealthFeedView.Listener> captor = ArgumentCaptor.forClass(HealthFeedView.Listener.class);
        verify(view, atLeastOnce()).setListener(captor.capture());
        return captor.getValue();
    }
}
