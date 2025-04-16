package com.finishherlezlah.error;

public class RateLimitException extends RuntimeException {

    private final String errorCode;
    private final String errorDescription;

    public RateLimitException(String errorCode, String errorDescription, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
