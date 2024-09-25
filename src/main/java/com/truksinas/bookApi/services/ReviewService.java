package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.ReviewEntity;
import com.truksinas.bookApi.responses.PaginatedApiResponse;

public interface ReviewService {
    ReviewEntity createReview(Integer bookId, ReviewDto reviewDto);
    ReviewEntity getReviewById(Integer id);
    void deleteReviewById(Integer id);
    ReviewEntity updateReview(Integer id, ReviewDto reviewDto);
    PaginatedApiResponse<ReviewDto> getAllReviews(Integer currentPage, Integer pageSize, Integer stars);
    PaginatedApiResponse<ReviewDto> getAllBookReviews(Integer bookId, Integer currentPage, Integer pageSize, Integer stars);
}
