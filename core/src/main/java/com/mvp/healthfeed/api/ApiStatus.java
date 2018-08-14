package com.mvp.healthfeed.api;

public class ApiStatus {
    private final int code;
    private final String message;

    ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
