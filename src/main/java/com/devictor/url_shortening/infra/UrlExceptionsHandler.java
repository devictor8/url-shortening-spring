package com.devictor.url_shortening.infra;

import com.devictor.url_shortening.exceptions.InvalidUrlException;
import com.devictor.url_shortening.exceptions.UrlAlreadyInUseException;
import com.devictor.url_shortening.exceptions.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UrlExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    private ResponseEntity<RestErrorMessage> invalidUrlHandler(InvalidUrlException e) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    private ResponseEntity<RestErrorMessage> urlNotFoundHandler(UrlNotFoundException e) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler
    private ResponseEntity<RestErrorMessage> urlAlreadyInUseHandler(UrlAlreadyInUseException e) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
