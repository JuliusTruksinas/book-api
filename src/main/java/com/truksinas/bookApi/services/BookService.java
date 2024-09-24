package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.responses.ApiResponse;

public interface BookService {
    BookEntity createBook(BookDto bookDto);
    BookEntity getBookById(int id);
    void deleteBookById(int id);
}
