package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvp.healthfeed.FeedViewState;

public class QnAViewHolder extends FeedViewHolder{

    QnAViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_QNA);
    }

    @Override
    public void bind(FeedViewState feedViewState) {

    }
}
