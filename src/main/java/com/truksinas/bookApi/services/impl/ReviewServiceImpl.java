package com.truksinas.bookApi.services.impl;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.entities.ReviewEntity;
import com.truksinas.bookApi.exceptions.ReviewNotFoundException;
import com.truksinas.bookApi.repositories.BookRepository;
import com.truksinas.bookApi.repositories.ReviewRepository;
import com.truksinas.bookApi.responses.PaginatedApiResponse;
import com.truksinas.bookApi.services.BookService;
import com.truksinas.bookApi.services.ReviewService;
import com.truksinas.bookApi.specifications.ReviewSpecification;
import com.truksinas.bookApi.utils.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.truksinas.bookApi.responses.ApiResponseStatus.SUCCESS;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private BookService bookService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository, BookService bookService) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Override
    public ReviewEntity createReview(Integer bookId, ReviewDto reviewDto) {
        BookEntity book = bookService.getBookById(bookId);

        ReviewEntity review = ReviewMapper.mapToEntity(reviewDto);

        review.setBook(book);

        ReviewEntity savedReview = reviewRepository.save(review);

        recalculateBookRating(bookId);

        return savedReview;
    }

    @Override
    public ReviewEntity getReviewById(Integer id) {
        ReviewEntity review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));

        return review;
    }

    @Override
    public void deleteReviewById(Integer id) {
        ReviewEntity review = getReviewById(id);

        reviewRepository.delete(review);
    }

    @Override
    public ReviewEntity updateReview(Integer id, ReviewDto reviewDto) {
        ReviewEntity review = getReviewById(id);

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        ReviewEntity updatedReview = reviewRepository.save(review);

        return updatedReview;
    }

    @Override
    public PaginatedApiResponse<ReviewDto> getAllReviews(Integer currentPage, Integer pageSize, Integer stars) {
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);
        Specification<ReviewEntity> spec = Specification.where(ReviewSpecification.hasStars(stars));

        Page<ReviewEntity> reviewPage = reviewRepository.findAll(spec, pageable);

        List<ReviewDto> data = reviewPage.getContent().stream().map(ReviewMapper::mapToDto).toList();

        return PaginatedApiResponse.<ReviewDto>builder()
                .status(SUCCESS.toString())
                .data(data)
                .currentPage(currentPage)
                .totalPages(reviewPage.getTotalPages())
                .totalItems(reviewPage.getTotalElements())
                .build();

    }

    @Override
    public PaginatedApiResponse<ReviewDto> getAllBookReviews(Integer bookId, Integer currentPage, Integer pageSize, Integer stars) {PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);
        BookEntity book = bookService.getBookById(bookId);

        Specification<ReviewEntity> spec = Specification.where(ReviewSpecification.hasStars(stars))
                .and(ReviewSpecification.hasBookId(bookId));

        Page<ReviewEntity> reviewPage = reviewRepository.findAll(spec, pageable);

        List<ReviewDto> data = reviewPage.getContent().stream().map(ReviewMapper::mapToDto).toList();

        return PaginatedApiResponse.<ReviewDto>builder()
                .status(SUCCESS.toString())
                .data(data)
                .currentPage(currentPage)
                .totalPages(reviewPage.getTotalPages())
                .totalItems(reviewPage.getTotalElements())
                .build();
    }

    private void recalculateBookRating(Integer bookId) {
        Double newRating = reviewRepository.findRatingByBookId(bookId);
        newRating = Math.round(newRating * 100.0) / 100.0;

        bookRepository.updateBookRating(bookId, newRating);
    }
}
