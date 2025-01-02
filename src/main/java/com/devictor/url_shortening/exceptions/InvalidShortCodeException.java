package com.devictor.url_shortening.exceptions;

public class InvalidShortCodeException extends RuntimeException {
    public InvalidShortCodeException(String message) {
        super(message);
    }

    public InvalidShortCodeException() {
        super();
    }
}
