package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.healthfeed.FeedViewState;
import com.mvp.healthfeed.HealthQnaViewState;
import com.mvp.healthfeed.HealthQuizViewState;

public class QuizViewHolder extends FeedViewHolder {
    private final TextView txtVwTitle;
    private final TextView txtVwBody;
    private final ImageView imgVwBanner;

    QuizViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_QUIZ);
        imgVwBanner = itemView.findViewById(R.id.imgVw_quiz_banner);
        txtVwTitle = itemView.findViewById(R.id.txtVw_quiz_title);
        txtVwBody = itemView.findViewById(R.id.txtVw_quiz_body);
    }

    @Override
    public void bind(FeedViewState viewState) {
        HealthQuizViewState quizViewState = (HealthQuizViewState) viewState;
        //imgVwBanner.setVisibility(quizViewState.bannerImageVisibilty());
        txtVwBody.setText(quizViewState.body());
        txtVwTitle.setText(quizViewState.title());
    }

    @Override
    public void unbind() {
        super.unbind();
    }
}
