package com.mvp.common;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class AndroidAssetLoader implements AssetLoader {

    private final Context context;

    public AndroidAssetLoader(Context context) {
        this.context = context;
    }

    @Override
    public InputStream loadAsset(String name) throws IOException {
        return context.getAssets().open(name);
    }
}
