package com.truksinas.bookApi.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(int id) {
        super("Review with id: " + id + " could not be found");
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
