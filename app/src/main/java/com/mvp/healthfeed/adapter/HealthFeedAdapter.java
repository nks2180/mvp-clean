package com.mvp.healthfeed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvp.healthfeed.FeedViewState;
import com.mvp.image.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HealthFeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private final LayoutInflater layoutInflater;
    private final ImageLoader imageLoader;
    private List<FeedViewState> viewStates = Collections.emptyList();

    public HealthFeedAdapter(LayoutInflater layoutInflater, ImageLoader imageLoader) {
        this.layoutInflater = layoutInflater;
        this.imageLoader = imageLoader;
    }

    public void setViewStates(List<FeedViewState> viewStates) {
        this.viewStates = new ArrayList<>(viewStates);
        notifyDataSetChanged();
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeedViewState.Type type = FeedViewState.Type.from(viewType);
        switch (type) {
            case FEED_AD:
                return new AdViewHolder(layoutInflater, parent, imageLoader);
            case FEED_QNA:
                return new QnAViewHolder(layoutInflater, parent, imageLoader);
            case FEED_QUIZ:
                return new QuizViewHolder(layoutInflater, parent, imageLoader);
            case FEED_TIP:
                return new TipViewHolder(layoutInflater, parent, imageLoader);
            default:
                throw new IllegalArgumentException("Can't recoginse view type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.bind(getViewStateAtPosition(position));
    }

    @Override
    public void onViewRecycled(FeedViewHolder holder) {
        holder.unbind();
    }

    @Override
    public int getItemViewType(int position) {
        FeedViewState componentViewState = viewStates.get(position);
        FeedViewState.Type type = componentViewState.type();
        return type.value();
    }

    @Override
    public int getItemCount() {
        return viewStates.size();
    }

    private FeedViewState getViewStateAtPosition(int position) {
        return viewStates.get(position);
    }
}
