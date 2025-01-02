package com.devictor.url_shortening.infra;

import com.devictor.url_shortening.exceptions.InvalidUrlExeceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UrlExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUrlExeceptions.class)
    private ResponseEntity<RestErrorMessage> invalidUrlHandler(InvalidUrlExeceptions e) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
