package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.healthfeed.FeedViewState;
import com.mvp.healthfeed.HealthQnaViewState;

public class QnAViewHolder extends FeedViewHolder {


    private final TextView txtVwTitle;
    private final TextView txtVwBody;
    private final ImageView imgVwBanner;

    QnAViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_QNA);
        imgVwBanner = itemView.findViewById(R.id.imgVw_qna_banner);
        txtVwTitle = itemView.findViewById(R.id.txtVw_qna_title);
        txtVwBody = itemView.findViewById(R.id.txtVw_qna_body);
    }

    @Override
    public void bind(FeedViewState viewState) {
        HealthQnaViewState qnaViewState = (HealthQnaViewState) viewState;
        imgVwBanner.setVisibility(qnaViewState.bannerImageVisibilty());
        txtVwBody.setText(qnaViewState.body());
        txtVwTitle.setText(qnaViewState.title());
    }

    @Override
    public void unbind() {
        super.unbind();
    }
}
