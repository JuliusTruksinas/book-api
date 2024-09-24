package com.truksinas.bookApi.exceptions;

import com.truksinas.bookApi.responses.ApiResponseStatus;
import com.truksinas.bookApi.responses.ErrorApiResponse;
import com.truksinas.bookApi.responses.FailApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<FailApiResponse> handleNotFoundExceptions(RuntimeException exception, WebRequest request) {
        Map<String, String> badRequestErrors = new HashMap<>();
        badRequestErrors.put("id", exception.getMessage());

        return new ResponseEntity<FailApiResponse>(
                new FailApiResponse(ApiResponseStatus.FAIL.toString().toLowerCase(), badRequestErrors),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiResponse> handleInternalServerErrors(RuntimeException exception, WebRequest request) {
        return new ResponseEntity<ErrorApiResponse>(
                new ErrorApiResponse(ApiResponseStatus.ERROR.toString().toLowerCase(), "Internal server error"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}