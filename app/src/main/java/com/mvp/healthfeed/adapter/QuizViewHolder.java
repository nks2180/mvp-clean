package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvp.healthfeed.FeedViewState;

public class QuizViewHolder extends FeedViewHolder {

    QuizViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_QUIZ);
    }

    @Override
    public void bind(FeedViewState feedViewState) {

    }
}
