package com.mvp.common;

import java.io.IOException;
import java.io.InputStream;

public interface AssetLoader {

    InputStream loadAsset(String name) throws IOException;
}
