package com.truksinas.bookApi.exceptions;

import com.truksinas.bookApi.responses.ErrorApiResponse;
import com.truksinas.bookApi.responses.FailApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static com.truksinas.bookApi.responses.ApiResponseStatus.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<FailApiResponse> handleNotFoundExceptions(RuntimeException exception, WebRequest request) {
        Map<String, String> badRequestErrors = new HashMap<>();
        badRequestErrors.put("id", exception.getMessage());

        return new ResponseEntity<FailApiResponse>(
                new FailApiResponse(FAIL.toString(), badRequestErrors),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiResponse> handleInternalServerErrors(RuntimeException exception, WebRequest request) {
        System.out.println(exception.getMessage());
        ErrorApiResponse response = new ErrorApiResponse(ERROR.toString(), "Internal server error");

        return new ResponseEntity<ErrorApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}