package com.mvp.network;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;

import java.io.IOException;

public class NullJsonAdapter<T> extends JsonAdapter<T> {

    @Override
    public T fromJson(JsonReader reader) throws IOException {
        return null;
    }

    @Override
    public void toJson(JsonWriter writer, T value) throws IOException {
        throw new UnsupportedOperationException();
    }
}
