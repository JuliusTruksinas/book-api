package com.truksinas.bookApi.controllers;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.responses.ApiResponse;
import com.truksinas.bookApi.services.BookService;
import com.truksinas.bookApi.utils.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.truksinas.bookApi.responses.ApiResponseStatus.*;

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
    public ResponseEntity<ApiResponse<BookDto>> getBook(@PathVariable(value = "id") int id) {
        BookDto bookDto = BookMapper.mapToDto(bookService.getBookById(id));
        ApiResponse<BookDto> response = new ApiResponse<>(SUCCESS.toString(), bookDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookDto>> createBook(@RequestBody BookDto bookDto) {
        BookDto createdBookDto = BookMapper.mapToDto(bookService.createBook(bookDto));
        ApiResponse<BookDto> response = new ApiResponse<>(SUCCESS.toString(), createdBookDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateBook(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>("Book with id: " + id + " successfully updated", HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable(value = "id") int id) {
        bookService.deleteBookById(id);
        ApiResponse<Void> response = new ApiResponse<>(SUCCESS.toString(), null);

        return ResponseEntity.ok(response);
    }
}
