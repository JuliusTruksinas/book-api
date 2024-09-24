package com.truksinas.bookApi.services.impl;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.exceptions.BookNotFoundException;
import com.truksinas.bookApi.repositories.BookRepository;
import com.truksinas.bookApi.services.BookService;
import com.truksinas.bookApi.utils.BookMapper;
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
    public BookEntity createBook(BookDto bookDto) {
        BookEntity book = BookMapper.mapToEntity(bookDto);

        BookEntity savedBook = bookRepository.save(book);

        return savedBook;
    }

    @Override
    public BookEntity getBookById(int id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return book;
    }

    @Override
    public void deleteBookById(int id) {
        BookEntity book = getBookById(id);
        bookRepository.delete(book);
    }

    @Override
    public BookEntity updateBook(int id, BookDto bookDto) {
        BookEntity book = getBookById(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setReleaseYear(bookDto.getReleaseYear());
        book.setPrice(bookDto.getPrice());

        BookEntity updatedBook = bookRepository.save(book);

        return updatedBook;
    }
}
