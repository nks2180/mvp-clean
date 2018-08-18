package com.mvp.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class AndroidNavigator implements Navigator {

    private final Activity activity;

    public AndroidNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void toTipDetail() {
        startActivity();
    }

    @Override
    public void toQnaDetail() {
        startActivity();
    }

    @Override
    public void toQuizDetail() {
        startActivity();
    }

    @Override
    public void toAdDetail() {
        startActivity();
    }

    private void startActivity() {
        String url = "https://www.google.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);

    }
}
