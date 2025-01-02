package com.devictor.url_shortening.exceptions;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message) {
        super(message);
    }

    public UrlNotFoundException() {
        super("URL not found");
    }
}
