package com.truksinas.bookApi.controllers;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.dtos.ReviewFilterDto;
import com.truksinas.bookApi.responses.ApiResponse;
import com.truksinas.bookApi.responses.PaginatedApiResponse;
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

    @GetMapping("/reviews")
    public ResponseEntity<PaginatedApiResponse<ReviewDto>> getAllReviews(@Valid @ModelAttribute ReviewFilterDto reviewFilterDto) {
        PaginatedApiResponse<ReviewDto> response = reviewService.getAllReviews(
                reviewFilterDto.getCurrentPage(),
                reviewFilterDto.getPageSize(),
                reviewFilterDto.getStars()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/books/{bookId}/reviews")
    public ResponseEntity<PaginatedApiResponse<ReviewDto>> getBookReviews(@PathVariable(value = "bookId") Integer bookId, @Valid @ModelAttribute ReviewFilterDto reviewFilterDto) {
        PaginatedApiResponse<ReviewDto> response = reviewService.getAllBookReviews(
                bookId,
                reviewFilterDto.getCurrentPage(),
                reviewFilterDto.getPageSize(),
                reviewFilterDto.getStars()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ApiResponse<ReviewDto>> getReview(@PathVariable(value = "id") Integer id) {
        ReviewDto reviewDto = ReviewMapper.mapToDto(reviewService.getReviewById(id));
        ApiResponse<ReviewDto> response = new ApiResponse<>(SUCCESS.toString(), reviewDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<ApiResponse<ReviewDto>> createReview(@PathVariable(value = "bookId") Integer bookId, @Valid @RequestBody ReviewDto reviewDto) {
        ReviewDto createdReviewDto = ReviewMapper.mapToDto(reviewService.createReview(bookId, reviewDto));

        ApiResponse<ReviewDto> response = new ApiResponse<>(SUCCESS.toString(), createdReviewDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<ApiResponse<ReviewDto>> updateReview(@PathVariable(value = "id") Integer id, @Valid @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReviewDto = ReviewMapper.mapToDto(reviewService.updateReview(id, reviewDto));
        ApiResponse<ReviewDto> response = new ApiResponse<>(SUCCESS.toString(), updatedReviewDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable(value = "id") Integer id) {
        reviewService.deleteReviewById(id);
        ApiResponse<Void> response = new ApiResponse<>(SUCCESS.toString(), null);

        return ResponseEntity.ok(response);

    }

}
