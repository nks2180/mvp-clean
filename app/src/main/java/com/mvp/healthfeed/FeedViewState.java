package com.mvp.healthfeed;

import android.support.annotation.LayoutRes;

import com.mvp.R;

public interface FeedViewState {
    enum Type {
        FEED_AD(R.layout.feed_ad),
        FEED_TIP(R.layout.feed_tip),
        FEED_QNA(R.layout.feed_qna),
        FEED_QUIZ(R.layout.feed_quiz);

        public static Type from(int value) {
            return Type.values()[value];
        }

        @LayoutRes
        private final int layoutId;

        Type(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public int value() {
            return ordinal();
        }
    }

    Type type();
}
