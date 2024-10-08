package com.truksinas.bookApi.services.impl;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.exceptions.BookNotFoundException;
import com.truksinas.bookApi.repositories.BookRepository;
import com.truksinas.bookApi.repositories.ReviewRepository;
import com.truksinas.bookApi.responses.PaginatedApiResponse;
import com.truksinas.bookApi.services.BookService;
import com.truksinas.bookApi.specifications.BookSpecification;
import com.truksinas.bookApi.utils.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.truksinas.bookApi.responses.ApiResponseStatus.*;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public BookEntity createBook(BookDto bookDto) {
        BookEntity book = BookMapper.mapToEntity(bookDto);

        BookEntity savedBook = bookRepository.save(book);

        return savedBook;
    }

    @Override
    public BookEntity getBookById(Integer id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return book;
    }

    @Override
    public void deleteBookById(Integer id) {
        BookEntity book = getBookById(id);
        bookRepository.delete(book);
    }

    @Override
    public BookEntity updateBook(Integer id, BookDto bookDto) {
        BookEntity book = getBookById(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setReleaseYear(bookDto.getReleaseYear());
        book.setPrice(bookDto.getPrice());

        BookEntity updatedBook = bookRepository.save(book);

        return updatedBook;
    }

    @Override
    public PaginatedApiResponse<BookDto> getAllBooks(Integer currentPage, Integer pageSize, String title, String author, Integer releaseYear, Double higherOrEqualThanRating, Double lowerOrEqualThanRating) {
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);

        Specification<BookEntity> spec = Specification.where(BookSpecification.hasTitle(title))
                .and(BookSpecification.hasAuthor(author))
                .and(BookSpecification.hasReleaseYear(releaseYear))
                .and(BookSpecification.hasHigherOrEqualThanRating(higherOrEqualThanRating))
                .and(BookSpecification.hasLowerOrEqualThanRating(lowerOrEqualThanRating));

        Page<BookEntity> booksPage = bookRepository.findAll(spec, pageable);

        List<BookDto> data = booksPage
                .getContent()
                .stream()
                .map(BookMapper::mapToDto).toList();

        return PaginatedApiResponse.<BookDto>builder()
                .status(SUCCESS.toString())
                .data(data)
                .currentPage(currentPage)
                .totalPages(booksPage.getTotalPages())
                .totalItems(booksPage.getTotalElements())
                .build();
    }
}
