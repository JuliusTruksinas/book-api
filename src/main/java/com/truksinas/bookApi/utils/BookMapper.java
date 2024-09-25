package com.truksinas.bookApi.utils;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;

public class BookMapper {
    public static BookDto mapToDto(BookEntity book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseYear(),
                book.getPrice(),
                book.getRating()
        );
    }

    public static BookEntity mapToEntity(BookDto bookDto) {
        BookEntity book = new BookEntity();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setReleaseYear(bookDto.getReleaseYear());
        book.setPrice(bookDto.getPrice());
        book.setRating(bookDto.getRating());

        return book;
    }
}