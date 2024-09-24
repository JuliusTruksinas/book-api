package com.truksinas.bookApi.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super("Book with id: " + id + " could not be found");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
