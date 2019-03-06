package com.example.tradelog.api.spec.exception;

public enum ExceptionCode {
    UNKNOWN("Unknown"),
    BAD_REQUEST("Bad request"),
    TRADELOG_EMPTY("Tradelog is empty");


    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
