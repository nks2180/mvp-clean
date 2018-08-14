package com.mvp.common;

public interface AnalyticsReporter {
    void init();

    AnalyticsReporter DUMMY = new AnalyticsReporter() {
        @Override
        public void init() {
            // no op
        }
    };
}
