package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.healthfeed.FeedViewState;
import com.mvp.healthfeed.HealthAdViewState;
import com.mvp.healthfeed.HealthQnaViewState;
import com.mvp.image.ImageLoader;

public class QnAViewHolder extends FeedViewHolder {


    private final TextView txtVwTitle;
    private final TextView txtVwBody;
    private final ImageView imgVwBanner;
    private final ImageLoader imageLoader;
    private final Listener listener;

    QnAViewHolder(LayoutInflater layoutInflater, ViewGroup parent, ImageLoader imageLoader, Listener listener) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_QNA);
        this.imageLoader = imageLoader;
        this.listener = listener;
        imgVwBanner = itemView.findViewById(R.id.imgVw_qna_banner);
        txtVwTitle = itemView.findViewById(R.id.txtVw_qna_title);
        txtVwBody = itemView.findViewById(R.id.txtVw_qna_body);
    }

    @Override
    public void bind(FeedViewState viewState) {
        final HealthQnaViewState qnaViewState = (HealthQnaViewState) viewState;
        txtVwBody.setText(qnaViewState.body());
        txtVwTitle.setText(qnaViewState.title());
        imgVwBanner.setVisibility(qnaViewState.bannerImageVisibility());
        imageLoader.load(qnaViewState.bannerImagePath())
                .into(imgVwBanner);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onQnAFeedTapped(qnaViewState);
            }
        });
    }

    @Override
    public void unbind() {
        super.unbind();
        imageLoader.clear(imgVwBanner);
        itemView.setOnClickListener(null);
    }

    interface Listener {
        void onQnAFeedTapped(HealthQnaViewState viewState);
    }
}
