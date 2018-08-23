package com.mvp.healthfeed;

import com.mvp.Navigation.Navigator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HealthFeedActivityPresenterTest {

    private static final HealthFeedViewState HEALTH_FEED_VIEW_STATE = HealthFeedIdleViewState
            .create(Collections.<FeedViewState>emptyList());

    @Mock private HealthFeedDisplayer displayer;
    @Mock private HealthFeedUseCase useCase;
    @Mock private Navigator navigator;
    @Mock private HealthQnaViewState QNA_VIEW_STATE;
    @Mock private HealthQuizViewState QUIZ_VIEW_STATE;
    @Mock private HealthTipViewState TIP_VIEW_STATE;
    @Mock private HealthAdViewState AD_VIEW_STATE;

    private HealthFeedActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        when(useCase.healthFeed()).thenReturn(Observable.just(HEALTH_FEED_VIEW_STATE));
        presenter = new HealthFeedActivityPresenter(useCase, displayer, navigator);
    }

    @Test
    public void shouldUpdateViewWhenHealthFeedResponseCome() throws Exception {
        presenter.startPresenting();

        verify(displayer).show(HEALTH_FEED_VIEW_STATE);
    }

    @Test
    public void shouldStopPresentingWhenStopPresentingIsCalled() throws Exception {
        presenter.startPresenting();

        presenter.stopPresenting();

        verify(displayer).setListener(HealthFeedView.Listener.NO_OP);
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
        verify(displayer, atLeastOnce()).setListener(captor.capture());
        return captor.getValue();
    }
}
