package com.mvp.healthfeed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.mvp.R;
import com.mvp.healthfeed.adapter.HealthFeedAdapter;
import com.mvp.image.ImageLoader;

import java.util.List;

class HealthFeedView {

    private final HealthFeedAdapter adapter;

    static HealthFeedView from(HealthFeedActivity activity, ImageLoader imageLoader) {
        RecyclerView recyclerView = activity.findViewById(R.id.recyclerVw_feed);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        HealthFeedAdapter adapter = new HealthFeedAdapter(LayoutInflater.from(activity), imageLoader);
        recyclerView.setAdapter(adapter);
        return new HealthFeedView(adapter);
    }

    private HealthFeedView(HealthFeedAdapter adapter) {
        this.adapter = adapter;
    }

    void show(List<FeedViewState> viewStates) {
        adapter.setViewStates(viewStates);
    }

    public void setListener(final Listener listener) {
        adapter.setListener(new HealthFeedAdapter.Listener() {
            @Override
            public void onAdFeedTapped(HealthAdViewState adViewState) {
                listener.onAdFeedTapped(adViewState);
            }

            @Override
            public void onQnAFeedTapped(HealthQnaViewState viewState) {
                listener.onQnAFeedTapped(viewState);
            }

            @Override
            public void onQuizFeedTapped(HealthQuizViewState viewState) {
                listener.onQuizFeedTapped(viewState);
            }

            @Override
            public void onTipFeedTapped(HealthTipViewState viewState) {
                listener.onTipFeedTapped(viewState);
            }
        });
    }

    interface Listener {

        Listener NO_OP = new Listener() {
            @Override
            public void onQuizFeedTapped(HealthQuizViewState adViewState) {
                // no op
            }

            @Override
            public void onQnAFeedTapped(HealthQnaViewState adViewState) {
                // no op
            }

            @Override
            public void onAdFeedTapped(HealthAdViewState adViewState) {
                // no op
            }

            @Override
            public void onTipFeedTapped(HealthTipViewState adViewState) {
                // no op
            }
        };

        void onQuizFeedTapped(HealthQuizViewState adViewState);

        void onQnAFeedTapped(HealthQnaViewState adViewState);

        void onAdFeedTapped(HealthAdViewState adViewState);

        void onTipFeedTapped(HealthTipViewState adViewState);
    }
}
