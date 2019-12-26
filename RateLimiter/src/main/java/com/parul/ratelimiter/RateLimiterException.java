package com.parul.ratelimiter;

public class RateLimiterException extends Exception {

    public RateLimiterException(String message) {
        super(message);
    }

    public RateLimiterException(String message, Exception e) {
        super(message, e);
    }
}
