package com.tokio.demo.exception;

/**This is a personalised exception for the Rating class*/
public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(String message) {
        super(message);
    }
}
