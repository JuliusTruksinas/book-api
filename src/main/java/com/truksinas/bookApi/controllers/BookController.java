package com.truksinas.bookApi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping()
    public ResponseEntity<String> getBooks() {
        return new ResponseEntity<>("All books", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getBook(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>("Book with id: " + id, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBook() {
        return new ResponseEntity<>("Book created", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateBook(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>("Book with id: " + id + " successfully updated", HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
