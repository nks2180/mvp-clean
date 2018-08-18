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

public class AdViewHolder extends FeedViewHolder {

    private final TextView txtVwTitle;
    private final TextView txtVwBody;
    private final ImageView imgVwBanner;
    private final ImageLoader imageLoader;
    private final Listener listener;


    AdViewHolder(LayoutInflater layoutInflater, ViewGroup parent, ImageLoader imageLoader, Listener listener) {
        super(layoutInflater, parent, FeedViewState.Type.FEED_AD);
        this.imageLoader = imageLoader;
        this.listener = listener;
        imgVwBanner = itemView.findViewById(R.id.imgVw_ad_banner);
        txtVwTitle = itemView.findViewById(R.id.txtVw_ad_title);
        txtVwBody = itemView.findViewById(R.id.txtVw_ad_body);
    }

    @Override
    public void bind(FeedViewState viewState) {
        final HealthAdViewState adViewState = (HealthAdViewState) viewState;
        txtVwBody.setText(adViewState.body());
        txtVwTitle.setText(adViewState.title());
        imgVwBanner.setVisibility(adViewState.bannerImageVisibility());
        imageLoader.load(adViewState.bannerImagePath())
                .into(imgVwBanner);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAdFeedTapped(adViewState);
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
        void onAdFeedTapped(HealthAdViewState adViewState);
    }
}
