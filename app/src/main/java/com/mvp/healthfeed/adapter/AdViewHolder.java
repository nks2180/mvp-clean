package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.healthfeed.FeedViewState;
import com.mvp.healthfeed.HealthAdViewState;
import com.mvp.healthfeed.HealthQnaViewState;

public class AdViewHolder extends FeedViewHolder{

    private final TextView txtVwTitle;
    private final TextView txtVwBody;
    private final ImageView imgVwBanner;


    AdViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_AD);
        imgVwBanner = itemView.findViewById(R.id.imgVw_ad_banner);
        txtVwTitle = itemView.findViewById(R.id.txtVw_ad_title);
        txtVwBody = itemView.findViewById(R.id.txtVw_ad_body);
    }

    @Override
    public void bind(FeedViewState viewState) {
        HealthAdViewState adViewState = (HealthAdViewState) viewState;
        //imgVwBanner.setVisibility(qnaViewState.bannerImageVisibilty());
        txtVwBody.setText(adViewState.body());
        txtVwTitle.setText(adViewState.title());
    }

    @Override
    public void unbind() {
        super.unbind();
    }
}
