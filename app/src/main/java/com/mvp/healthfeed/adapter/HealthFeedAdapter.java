package com.mvp.healthfeed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvp.healthfeed.FeedViewState;
import com.mvp.healthfeed.HealthAdViewState;
import com.mvp.healthfeed.HealthQnaViewState;
import com.mvp.healthfeed.HealthQuizViewState;
import com.mvp.healthfeed.HealthTipViewState;
import com.mvp.image.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HealthFeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private final LayoutInflater layoutInflater;
    private final ImageLoader imageLoader;
    private List<FeedViewState> viewStates = Collections.emptyList();
    private final RelayingListener listener = new RelayingListener();

    public HealthFeedAdapter(LayoutInflater layoutInflater, ImageLoader imageLoader) {
        this.layoutInflater = layoutInflater;
        this.imageLoader = imageLoader;
    }

    public void setViewStates(List<FeedViewState> viewStates) {
        this.viewStates = new ArrayList<>(viewStates);
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener.wrap(listener);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeedViewState.Type type = FeedViewState.Type.from(viewType);
        switch (type) {
            case FEED_AD:
                return new AdViewHolder(layoutInflater, parent, imageLoader, listener);
            case FEED_QNA:
                return new QnAViewHolder(layoutInflater, parent, imageLoader, listener);
            case FEED_QUIZ:
                return new QuizViewHolder(layoutInflater, parent, imageLoader, listener);
            case FEED_TIP:
                return new TipViewHolder(layoutInflater, parent, imageLoader, listener);
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

   public interface Listener extends TipViewHolder.Listener,
            QnAViewHolder.Listener,
            QuizViewHolder.Listener,
            AdViewHolder.Listener {

        Listener NO_OP = new Listener() {


            @Override
            public void onQuizFeedTapped(HealthQuizViewState viewState) {
                // no op
            }

            @Override
            public void onQnAFeedTapped(HealthQnaViewState viewState) {
                // no op
            }

            @Override
            public void onAdFeedTapped(HealthAdViewState adViewState) {
                // no op
            }

            @Override
            public void onTipFeedTapped(HealthTipViewState viewState) {
                // no op
            }
        };
    }
}
