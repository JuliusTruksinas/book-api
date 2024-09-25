package com.truksinas.bookApi.controllers;

import com.truksinas.bookApi.dtos.ReviewDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.entities.ReviewEntity;
import com.truksinas.bookApi.services.ReviewService;
import com.truksinas.bookApi.utils.ReviewMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void testCreateReview() throws Exception {
        ReviewDto reviewDto = new ReviewDto(null, "Review Title", "Great book!", 5);
        BookEntity book = new BookEntity();
        Date now = new Date();
        ReviewEntity createdReviewEntity = ReviewEntity.builder()
                .id(1)
                .title("Review Title")
                .content("Great book!")
                .stars(5)
                .book(book)
                .createdAt(now)
                .updatedAt(now)
                .build();

        ReviewDto createdReviewDto = ReviewMapper.mapToDto(createdReviewEntity);

        Mockito.when(reviewService.createReview(Mockito.anyInt(), Mockito.any(ReviewDto.class)))
                .thenReturn(createdReviewEntity);

        mockMvc.perform(post("/books/1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Review Title\",\"content\":\"Great book!\",\"stars\":5}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"status\":\"success\",\"data\":{\"id\":1,\"title\":\"Review Title\",\"content\":\"Great book!\",\"stars\":5}}"));
    }

    @Test
    public void testGetReview() throws Exception {
        Date now = new Date();

        ReviewEntity reviewEntity = ReviewEntity.builder()
                .id(1)
                .title("Review Title")
                .content("Great book!")
                .stars(5)
                .createdAt(now)
                .updatedAt(now)
                .build();

        ReviewDto reviewDto = ReviewMapper.mapToDto(reviewEntity);

        Mockito.when(reviewService.getReviewById(1)).thenReturn(reviewEntity);

        mockMvc.perform(get("/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"success\",\"data\":{\"id\":1,\"title\":\"Review Title\",\"content\":\"Great book!\",\"stars\":5}}"));
    }

    @Test
    public void testUpdateReview() throws Exception {
        ReviewDto reviewDto = new ReviewDto(1, "Updated Title", "Updated content", 4);
        BookEntity book = new BookEntity();
        Date now = new Date();
        ReviewEntity updatedReviewEntity = ReviewEntity.builder()
                .id(1)
                .title("Updated Title")
                .content("Updated content")
                .stars(4)
                .book(book)
                .createdAt(now)
                .updatedAt(now)
                .build();

        ReviewDto updatedReviewDto = ReviewMapper.mapToDto(updatedReviewEntity);

        Mockito.when(reviewService.updateReview(Mockito.anyInt(), Mockito.any(ReviewDto.class)))
                .thenReturn(updatedReviewEntity);

        mockMvc.perform(put("/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Updated Title\",\"content\":\"Updated content\",\"stars\":4}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"status\":\"success\",\"data\":{\"id\":1,\"title\":\"Updated Title\",\"content\":\"Updated content\",\"stars\":4}}"));
    }

    @Test
    public void testDeleteReview() throws Exception {
        Mockito.doNothing().when(reviewService).deleteReviewById(1);

        mockMvc.perform(delete("/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"success\",\"data\":null}"));
    }
}