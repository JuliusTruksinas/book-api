package com.truksinas.bookApi.controllers;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.responses.ApiResponse;
import com.truksinas.bookApi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<String> getBooks() {
        return new ResponseEntity<>("All books", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getBook(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>("Book with id: " + id, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookDto>> createBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
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
