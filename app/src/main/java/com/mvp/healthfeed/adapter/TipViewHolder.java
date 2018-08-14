package com.mvp.healthfeed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.healthfeed.FeedViewState;
import com.mvp.healthfeed.HealthQnaViewState;
import com.mvp.healthfeed.HealthTipViewState;

public class TipViewHolder extends FeedViewHolder{

    private final TextView txtVwTitle;
    private final TextView txtVwBody;
    private final ImageView imgVwBanner;

    TipViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_TIP);
        imgVwBanner = itemView.findViewById(R.id.imgVw_tip_banner);
        txtVwTitle = itemView.findViewById(R.id.txtVw_tip_title);
        txtVwBody = itemView.findViewById(R.id.txtVw_tip_body);
    }

    @Override
    public void bind(FeedViewState viewState) {
        HealthTipViewState tipViewState = (HealthTipViewState) viewState;
        //imgVwBanner.setVisibility(qnaViewState.bannerImageVisibilty());
        txtVwBody.setText(tipViewState.body());
        txtVwTitle.setText(tipViewState.title());
    }

    @Override
    public void unbind() {
        super.unbind();
    }
}
