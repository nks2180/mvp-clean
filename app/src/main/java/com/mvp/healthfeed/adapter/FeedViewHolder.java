package com.mvp.healthfeed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvp.healthfeed.FeedViewState;

abstract class FeedViewHolder extends RecyclerView.ViewHolder {

    FeedViewHolder(LayoutInflater layoutInflater, ViewGroup parent, FeedViewState.Type type) {
        super(layoutInflater.inflate(type.getLayoutId(), parent, false));
    }

    public abstract void bind(FeedViewState feedViewState);

    public void unbind() {
        // no op
    }
}
