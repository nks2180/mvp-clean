package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvp.healthfeed.FeedViewState;

public class TipViewHolder extends FeedViewHolder{

    TipViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_TIP);
    }

    @Override
    public void bind(FeedViewState feedViewState) {

    }
}
