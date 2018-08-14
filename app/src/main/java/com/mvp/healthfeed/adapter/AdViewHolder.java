package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvp.healthfeed.FeedViewState;

public class AdViewHolder extends FeedViewHolder{

    AdViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_AD);
    }

    @Override
    public void bind(FeedViewState feedViewState) {

    }
}
