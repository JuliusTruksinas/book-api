package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.responses.PaginatedApiResponse;

public interface BookService {
    BookEntity createBook(BookDto bookDto);
    BookEntity getBookById(Integer id);
    void deleteBookById(Integer id);
    BookEntity updateBook(Integer id, BookDto bookDto);
    PaginatedApiResponse<BookDto> getAllBooks(Integer currentPage, Integer pageSize, String title, String author, Integer releaseYear, Double higherOrEqualThanRating, Double lowerOrEqualThanRating);
}
