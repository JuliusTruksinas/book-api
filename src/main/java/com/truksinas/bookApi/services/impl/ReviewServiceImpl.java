package com.truksinas.bookApi.services.impl;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.entities.ReviewEntity;
import com.truksinas.bookApi.exceptions.ReviewNotFoundException;
import com.truksinas.bookApi.repositories.ReviewRepository;
import com.truksinas.bookApi.services.BookService;
import com.truksinas.bookApi.services.ReviewService;
import com.truksinas.bookApi.utils.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private BookService bookService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, BookService bookService) {
        this.reviewRepository = reviewRepository;
        this.bookService = bookService;
    }

    @Override
    public ReviewEntity createReview(int bookId, ReviewDto reviewDto) {
        BookEntity book = bookService.getBookById(bookId);

        ReviewEntity review = ReviewMapper.mapToEntity(reviewDto);

        review.setBook(book);

        ReviewEntity savedReview = reviewRepository.save(review);

        return savedReview;
    }

    @Override
    public ReviewEntity getReviewById(int id) {
        ReviewEntity review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));

        return review;
    }

    @Override
    public void deleteReviewById(int id) {
        ReviewEntity review = getReviewById(id);

        reviewRepository.delete(review);
    }
}
