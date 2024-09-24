package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;

public interface BookService {
    BookEntity createBook(BookDto bookDto);
    BookEntity getBookById(int id);
    void deleteBookById(int id);
    BookEntity updateBook(int id, BookDto bookDto);
}
