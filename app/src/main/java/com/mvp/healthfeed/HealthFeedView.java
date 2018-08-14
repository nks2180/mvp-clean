package com.mvp.healthfeed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvp.R;

class HealthFeedView {

    static HealthFeedView from(HealthFeedActivity activity) {
        RecyclerView recyclerView = activity.findViewById(R.id.recyclerVw_feed);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        return new HealthFeedView();
    }

    void show(HealthFeedViewState healthFeedViewState) {

    }
}
