package com.truksinas.bookApi.services.impl;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.repositories.BookRepository;
import com.truksinas.bookApi.responses.ApiResponse;
import com.truksinas.bookApi.responses.ApiResponseStatus;
import com.truksinas.bookApi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ApiResponse<BookDto> createBook(BookDto bookDto) {
        BookEntity book = mapToEntity(bookDto);

        BookEntity savedBook = bookRepository.save(book);

        return new ApiResponse<BookDto>(
                ApiResponseStatus.SUCCESS.toString().toLowerCase(),
                mapToDto(savedBook)
        );
    }

    private BookDto mapToDto(BookEntity book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setReleaseYear(book.getReleaseYear());
        bookDto.setPrice(book.getPrice());

        return bookDto;
    }

    private BookEntity mapToEntity(BookDto bookDto) {
        BookEntity book = new BookEntity();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setReleaseYear(bookDto.getReleaseYear());
        book.setPrice(bookDto.getPrice());

        return book;
    }
}
