package com.devictor.url_shortening.infra;

import org.springframework.http.HttpStatus;

public record RestErrorMessage(HttpStatus status, int errorCode, String message) {
}
