package com.mvp.test;

import com.squareup.moshi.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import okio.Buffer;

public class TestResources {
    private TestResources() {
        throw new UnsupportedOperationException("No instances allowed");
    }
    public static JsonReader jsonReaderFrom(String resourcePath) throws IOException {
        File file = Paths.get("./core/src/test/resources/" + resourcePath).toFile();
        FileInputStream stream = new FileInputStream(file);
        return JsonReader.of(new Buffer().readFrom(stream));
    }
}
