package com.truksinas.bookApi.utils;


import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.ReviewEntity;

public class ReviewMapper {
    public static ReviewDto mapToDto(ReviewEntity review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());

        return reviewDto;
    }

    public static ReviewEntity mapToEntity(ReviewDto reviewDto) {
        ReviewEntity review = new ReviewEntity();

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        return review;
    }
}
