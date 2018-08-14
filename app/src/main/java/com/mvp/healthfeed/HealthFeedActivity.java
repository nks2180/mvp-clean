package com.mvp.healthfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvp.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class HealthFeedActivity extends AppCompatActivity {

    @Inject HealthFeedActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
        presenter.startPresenting();
    }

    @Override
    protected void onDestroy() {
        presenter.stopPresenting();
        super.onDestroy();
    }
}
