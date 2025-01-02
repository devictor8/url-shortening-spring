package com.devictor.url_shortening.exceptions;

public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String message) {
        super(message);
    }

    public InvalidUrlException() {
        super();
    }
}
