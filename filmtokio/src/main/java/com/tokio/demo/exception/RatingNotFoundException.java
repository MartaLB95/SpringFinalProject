package com.tokio.demo.exception;

public class RatingNotFoundException extends RuntimeException{
        public RatingNotFoundException(String message) {
            super(message);
        }
    }
