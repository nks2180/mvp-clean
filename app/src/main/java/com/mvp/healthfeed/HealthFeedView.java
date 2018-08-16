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
}
