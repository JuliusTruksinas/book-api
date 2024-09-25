package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.ReviewEntity;
import com.truksinas.bookApi.responses.PaginatedApiResponse;

public interface ReviewService {
    ReviewEntity createReview(int bookId, ReviewDto reviewDto);
    ReviewEntity getReviewById(int id);
    void deleteReviewById(int id);
    ReviewEntity updateReview(int id, ReviewDto reviewDto);
    PaginatedApiResponse<ReviewDto> getAllReviews(Integer currentPage, Integer pageSize, Integer stars);
}
