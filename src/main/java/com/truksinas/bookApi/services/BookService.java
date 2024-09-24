package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.responses.ApiResponse;

public interface BookService {
    ApiResponse<BookDto> createBook(BookDto bookDto);
    ApiResponse<BookDto> getBookById(int id);
}
