package com.devictor.url_shortening.infra;

import com.devictor.url_shortening.exceptions.InvalidShortCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ShortCodeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidShortCodeException.class)
    private ResponseEntity<RestErrorMessage> invalidShortCodeHandler(InvalidShortCodeException e) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
