package com.mvp.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader {

    @Override
    public RequestCreator load(Uri uri) {
        com.squareup.picasso.RequestCreator requestCreator = Picasso.get().load(uri);
        return new PicassoRequestCreator(requestCreator);
    }

    @Override
    public RequestCreator load(String uri) {
        com.squareup.picasso.RequestCreator requestCreator = Picasso.get().load(uri);
        return new PicassoRequestCreator(requestCreator);
    }

    @Override
    public void clear(ImageView imageView) {
        Picasso.get().cancelRequest(imageView);
    }

    private static class PicassoRequestCreator implements RequestCreator {

        private Listener listener = Listener.NO_OP;

        com.squareup.picasso.RequestCreator requestCreator;

        PicassoRequestCreator(com.squareup.picasso.RequestCreator requestCreator) {
            this.requestCreator = requestCreator;
        }

        @Override
        public RequestCreator withPlaceholder(int drawableResourceId) {
            requestCreator.placeholder(drawableResourceId);
            return this;
        }

        @Override
        public RequestCreator withPlaceholder(Drawable drawable) {
            requestCreator.placeholder(drawable);
            return this;
        }

        @Override
        public RequestCreator withListener(Listener listener) {
            this.listener = listener;
            return this;
        }

        @Override
        public RequestCreator centerInside(int width, int height) {
            requestCreator.resize(width, height);
            requestCreator.centerInside();
            return this;
        }

        @Override
        public RequestCreator fit() {
           // requestCreator.fit();
            return this;
        }

        @Override
        public RequestCreator centerCrop(int width, int height) {
            requestCreator.resize(width, height);
            requestCreator.centerCrop();
            return this;
        }

        @Override
        public RequestCreator noCrossFade() {
            requestCreator.noFade();
            return this;
        }

        @Override
        public void into(ImageView imageView) {
            requestCreator.into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    listener.onSuccess();
                }

                @Override
                public void onError(Exception e) {
                    listener.onError(e);
                }
            });
        }
    }

}
