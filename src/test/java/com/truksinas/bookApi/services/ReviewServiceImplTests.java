package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.entities.ReviewEntity;
import com.truksinas.bookApi.exceptions.ReviewNotFoundException;
import com.truksinas.bookApi.repositories.BookRepository;
import com.truksinas.bookApi.repositories.ReviewRepository;
import com.truksinas.bookApi.services.impl.ReviewServiceImpl;
import com.truksinas.bookApi.utils.ReviewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ReviewServiceImplTests {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReview() {
        BookEntity book = new BookEntity();
        ReviewDto reviewDto = new ReviewDto(null, "Review Title", "Great book!", 5);
        ReviewEntity reviewEntity = ReviewMapper.mapToEntity(reviewDto);
        reviewEntity.setBook(book);

        when(bookService.getBookById(anyInt())).thenReturn(book);
        when(reviewRepository.save(any(ReviewEntity.class))).thenReturn(reviewEntity);
        doNothing().when(bookRepository).updateBookRating(anyInt(), anyDouble());

        ReviewEntity result = reviewService.createReview(1, reviewDto);

        assertNotNull(result);
        assertEquals("Review Title", result.getTitle());
        verify(reviewRepository, times(1)).save(any(ReviewEntity.class));
        verify(bookRepository, times(1)).updateBookRating(anyInt(), anyDouble());
    }

    @Test
    public void testGetReviewById() {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(1);
        reviewEntity.setTitle("Review Title");

        when(reviewRepository.findById(anyInt())).thenReturn(Optional.of(reviewEntity));

        ReviewEntity result = reviewService.getReviewById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Review Title", result.getTitle());
    }

    @Test
    public void testGetReviewById_NotFound() {
        when(reviewRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ReviewNotFoundException.class, () -> reviewService.getReviewById(1));
    }

    @Test
    public void testDeleteReviewById() {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(1);

        when(reviewRepository.findById(anyInt())).thenReturn(Optional.of(reviewEntity));
        doNothing().when(reviewRepository).delete(any(ReviewEntity.class));

        reviewService.deleteReviewById(1);

        verify(reviewRepository, times(1)).delete(any(ReviewEntity.class));
    }

    @Test
    public void testUpdateReview() {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(1);
        reviewEntity.setTitle("Old Title");

        ReviewDto reviewDto = new ReviewDto(1, "Updated Title", "Updated content", 4);

        when(reviewRepository.findById(anyInt())).thenReturn(Optional.of(reviewEntity));
        when(reviewRepository.save(any(ReviewEntity.class))).thenReturn(reviewEntity);

        ReviewEntity result = reviewService.updateReview(1, reviewDto);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(reviewRepository, times(1)).save(any(ReviewEntity.class));
    }
}