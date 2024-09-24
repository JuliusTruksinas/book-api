package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.responses.PaginatedApiResponse;

import java.util.List;

public interface BookService {
    BookEntity createBook(BookDto bookDto);
    BookEntity getBookById(int id);
    void deleteBookById(int id);
    BookEntity updateBook(int id, BookDto bookDto);
    PaginatedApiResponse<BookDto> getAllBooks(Integer currentPage, Integer pageSize, String title, String author, Integer year, Double rating);
}
