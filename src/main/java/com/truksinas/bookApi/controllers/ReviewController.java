package com.truksinas.bookApi.controllers;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.responses.ApiResponse;
import com.truksinas.bookApi.services.ReviewService;
import com.truksinas.bookApi.utils.ReviewMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.truksinas.bookApi.responses.ApiResponseStatus.*;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/books/{bookId}/reviews")
    public ResponseEntity<String> getBookReviews(@PathVariable(value = "bookId") int bookId) {
        return new ResponseEntity<>("All reviews for Book with id: " + bookId, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ApiResponse<ReviewDto>> getReview(@PathVariable(value = "id") int id) {
        ReviewDto reviewDto = ReviewMapper.mapToDto(reviewService.getReviewById(id));
        ApiResponse<ReviewDto> response = new ApiResponse<>(SUCCESS.toString(), reviewDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<ApiResponse<ReviewDto>> createReview(@PathVariable(value = "bookId") int bookId, @Valid @RequestBody ReviewDto reviewDto) {
        ReviewDto createdReviewDto = ReviewMapper.mapToDto(reviewService.createReview(bookId, reviewDto));

        ApiResponse<ReviewDto> response = new ApiResponse<>(SUCCESS.toString(), createdReviewDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<String> updateReview(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>("Review with id: " + id + " successfully updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable(value = "id") int id) {
        reviewService.deleteReviewById(id);
        ApiResponse<Void> response = new ApiResponse<>(SUCCESS.toString(), null);

        return ResponseEntity.ok(response);

    }

}
