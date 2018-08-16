package com.mvp.healthfeed.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApiFeedJsonAdapter extends JsonAdapter<ApiFeed> {

    private final JsonAdapter<List<ApiMedia>> mediaAdapter;

    ApiFeedJsonAdapter(JsonAdapter<List<ApiMedia>> mediaAdapter) {
        this.mediaAdapter = mediaAdapter;
    }

    @Override
    public ApiFeed fromJson(JsonReader reader) throws IOException {
        Map<String, Object> componentJson = (Map<String, Object>) reader.readJsonValue();
        String type = (String) componentJson.get("type");
        switch (type) {
            case "Ad":
                return readAd(componentJson);
            case "Tip":
                return readTip(componentJson);
            case "Question":
                return readQnA(componentJson);
            case "Quiz":
                return readQuiz(componentJson);
            default:
                return ApiFeed.UNKNOWN;
        }
    }

    private ApiFeed readQuiz(Map<String, Object> componentJson) {
        int code = Integer.parseInt((String) componentJson.get("code"));
        String title = (String) componentJson.get("title");
        String body = (String) componentJson.get("body");
        String tag = (String) componentJson.get("tag");
        String supportText = (String) componentJson.get("supportText");
        List<ApiMedia> mediaList = mediaAdapter.fromJsonValue(componentJson.get("mediaList"));
        return new ApiQuizFeed(code, title, body, tag, supportText, mediaList);
    }

    private ApiFeed readQnA(Map<String, Object> componentJson) {
        int code = Integer.parseInt((String) componentJson.get("code"));
        String title = (String) componentJson.get("title");
        String body = (String) componentJson.get("body");
        String tag = (String) componentJson.get("tag");
        String supportText = (String) componentJson.get("supportText");
        List<ApiMedia> mediaList = mediaAdapter.fromJsonValue(componentJson.get("mediaList"));
        return new ApiQnAFeed(code, title, body, tag, supportText, mediaList);
    }

    private ApiFeed readTip(Map<String, Object> componentJson) {
        int code = Integer.parseInt((String) componentJson.get("code"));
        String title = (String) componentJson.get("title");
        String body = (String) componentJson.get("body");
        String tag = (String) componentJson.get("tag");
        String supportText = (String) componentJson.get("supportText");
        List<ApiMedia> mediaList = mediaAdapter.fromJsonValue(componentJson.get("mediaList"));
        return new ApiTipFeed(code, title, body, tag, supportText, mediaList);
    }

    private ApiFeed readAd(Map<String, Object> componentJson) {
        int code = Integer.parseInt((String) componentJson.get("code"));
        String title = (String) componentJson.get("title");
        String body = (String) componentJson.get("body");
        String tag = (String) componentJson.get("tag");
        String supportText = (String) componentJson.get("supportText");
        List<ApiMedia> mediaList = mediaAdapter.fromJsonValue(componentJson.get("mediaList"));
        return new ApiAdFeed(code, title, body, tag, supportText, mediaList);
    }

    @Override
    public void toJson(JsonWriter writer, ApiFeed value) throws IOException {
        throw new UnsupportedOperationException("JSON serialization is not implemented yet");
    }

    public static ApiFeedJsonAdapter from(Moshi moshi) {
        JsonAdapter<List<ApiMedia>> apiMediaAdapter = moshi.adapter(Types.newParameterizedType(List.class, ApiMedia.class));
        return new ApiFeedJsonAdapter(apiMediaAdapter);
    }
}
