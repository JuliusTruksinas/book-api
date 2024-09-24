package com.truksinas.bookApi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    @GetMapping("/books/{bookId}/reviews")
    public ResponseEntity<String> getBookReviews(@PathVariable(value = "bookId") int bookId) {
        return new ResponseEntity<>("All reviews for Book with id: " + bookId, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<String> getReview(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>("Review with id: " + id, HttpStatus.OK);
    }

    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<String> createReview(@PathVariable(value = "bookId") int bookId) {
        return new ResponseEntity<>("Created a review for a book with id: " + bookId, HttpStatus.CREATED);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<String> updateReview(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>("Review with id: " + id + " successfully updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
