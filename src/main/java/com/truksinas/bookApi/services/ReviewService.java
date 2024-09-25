package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.ReviewEntity;

public interface ReviewService {
    ReviewEntity createReview(int bookId, ReviewDto reviewDto);
    ReviewEntity getReviewById(int id);
    void deleteReviewById(int id);
}
