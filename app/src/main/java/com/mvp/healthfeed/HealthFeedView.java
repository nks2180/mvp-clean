package com.mvp.healthfeed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.healthfeed.adapter.HealthFeedAdapter;
import com.mvp.image.ImageLoader;

import java.util.List;

class HealthFeedView {

    private final HealthFeedAdapter adapter;
    private final ProgressBar progressBar;
    private final TextView txtVwError;
    private final RecyclerView recyclerView;

    static HealthFeedView from(HealthFeedActivity activity, ImageLoader imageLoader) {
        RecyclerView recyclerView = activity.findViewById(R.id.recyclerVw_feed);
        ProgressBar progressBar = activity.findViewById(R.id.progress_bar);
        TextView txtVwError = activity.findViewById(R.id.txtVw_error);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        HealthFeedAdapter adapter = new HealthFeedAdapter(LayoutInflater.from(activity), imageLoader);
        recyclerView.setAdapter(adapter);
        return new HealthFeedView(adapter, progressBar, txtVwError, recyclerView);
    }

    private HealthFeedView(HealthFeedAdapter adapter,
                           ProgressBar progressBar,
                           TextView txtVwError,
                           RecyclerView recyclerView) {
        this.adapter = adapter;
        this.progressBar = progressBar;
        this.txtVwError = txtVwError;
        this.recyclerView = recyclerView;
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

    void show(List<FeedViewState> viewStates) {
        recyclerView.setVisibility(View.VISIBLE);
        txtVwError.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        adapter.setViewStates(viewStates);
    }

    void showLoading() {
        recyclerView.setVisibility(View.GONE);
        txtVwError.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    void showError() {
        recyclerView.setVisibility(View.GONE);
        txtVwError.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
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
