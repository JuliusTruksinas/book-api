package com.truksinas.bookApi.exceptions;

import com.truksinas.bookApi.responses.ErrorApiResponse;
import com.truksinas.bookApi.responses.FailApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiResponse> handleInternalServerErrors(RuntimeException exception, WebRequest request) {
        ErrorApiResponse response = new ErrorApiResponse(ERROR.toString(), exception.getMessage());

        return new ResponseEntity<ErrorApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailApiResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> badRequestErrors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                badRequestErrors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<FailApiResponse>(
                new FailApiResponse(FAIL.toString(), badRequestErrors),
                HttpStatus.BAD_REQUEST
        );
    }

}