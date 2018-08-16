package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.healthfeed.FeedViewState;
import com.mvp.healthfeed.HealthQnaViewState;
import com.mvp.healthfeed.HealthQuizViewState;
import com.mvp.image.ImageLoader;

public class QuizViewHolder extends FeedViewHolder {
    private final TextView txtVwTitle;
    private final TextView txtVwBody;
    private final ImageView imgVwBanner;
    private final ImageLoader imageLoader;

    QuizViewHolder(LayoutInflater layoutInflater, ViewGroup parent, ImageLoader imageLoader) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_QUIZ);
        this.imageLoader = imageLoader;
        imgVwBanner = itemView.findViewById(R.id.imgVw_quiz_banner);
        txtVwTitle = itemView.findViewById(R.id.txtVw_quiz_title);
        txtVwBody = itemView.findViewById(R.id.txtVw_quiz_body);
    }

    @Override
    public void bind(FeedViewState viewState) {
        HealthQuizViewState quizViewState = (HealthQuizViewState) viewState;
        txtVwBody.setText(quizViewState.body());
        txtVwTitle.setText(quizViewState.title());
        imgVwBanner.setVisibility(quizViewState.bannerImageVisibility());
        imageLoader.load(quizViewState.bannerImagePath())
                .into(imgVwBanner);
    }

    @Override
    public void unbind() {
        super.unbind();
        imageLoader.clear(imgVwBanner);
    }
}
