package com.mvp.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public interface ImageLoader {

    RequestCreator load(Uri uri);

    RequestCreator load(String uri);

    void clear(ImageView imageView);

    interface RequestCreator {

        RequestCreator withPlaceholder(@DrawableRes int drawableResourceId);

        RequestCreator withPlaceholder(Drawable drawable);

        RequestCreator withListener(Listener listener);

        RequestCreator centerInside(int width, int height);

        RequestCreator centerCrop(int width, int height);

        RequestCreator fit();

        RequestCreator noCrossFade();

        void into(ImageView imageView);
    }

    interface Listener {

        Listener NO_OP = new Listener() {
            @Override
            public void onError(Exception e) {
                // no op
            }

            @Override
            public void onSuccess() {
                // no op
            }
        };

        void onError(Exception e);

        void onSuccess();
    }

    interface Factory {

        ImageLoader create(Context context);
    }
}


