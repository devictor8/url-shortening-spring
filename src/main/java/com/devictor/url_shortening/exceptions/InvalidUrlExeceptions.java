package com.devictor.url_shortening.exceptions;

public class InvalidUrlExeceptions extends RuntimeException {
    public InvalidUrlExeceptions(String message) {
        super(message);
    }

    public InvalidUrlExeceptions() {
        super();
    }
}
