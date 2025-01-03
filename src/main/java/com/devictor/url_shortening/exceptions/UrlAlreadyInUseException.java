package com.devictor.url_shortening.exceptions;

public class UrlAlreadyInUseException extends RuntimeException {
    public UrlAlreadyInUseException(String message) {
        super(message);
    }

    public UrlAlreadyInUseException() {
        super("Url already in use.");
    }
}
