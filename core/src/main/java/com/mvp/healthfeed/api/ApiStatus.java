package com.mvp.healthfeed.api;

class ApiStatus {
    final int code;
    final String message;

    ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
