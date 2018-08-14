package com.mvp.network;

import com.squareup.moshi.Moshi;

public final class JsonDefaults {

    private static final Moshi MOSHI = new Moshi.Builder().build();

    private JsonDefaults() {
        throw new UnsupportedOperationException("No instance allowed!");
    }

    public static Moshi moshi() {
        return MOSHI;
    }

}
