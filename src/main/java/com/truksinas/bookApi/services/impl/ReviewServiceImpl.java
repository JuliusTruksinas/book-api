package com.truksinas.bookApi.services.impl;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.entities.ReviewEntity;
import com.truksinas.bookApi.exceptions.ReviewNotFoundException;
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

    @Override
    public ReviewEntity updateReview(int id, ReviewDto reviewDto) {
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
}
