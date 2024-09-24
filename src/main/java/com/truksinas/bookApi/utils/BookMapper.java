package com.truksinas.bookApi.utils;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;

public class BookMapper {
    public static BookDto mapToDto(BookEntity book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setReleaseYear(book.getReleaseYear());
        bookDto.setPrice(book.getPrice());

        return bookDto;
    }

    public static BookEntity mapToEntity(BookDto bookDto) {
        BookEntity book = new BookEntity();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setReleaseYear(bookDto.getReleaseYear());
        book.setPrice(bookDto.getPrice());

        return book;
    }
}
