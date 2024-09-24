package com.truksinas.bookApi.controllers;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.responses.ApiResponse;
import com.truksinas.bookApi.responses.PaginatedApiResponse;
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
    public ResponseEntity<PaginatedApiResponse<BookDto>> getBooks(
            @RequestParam(value = "currentPage", defaultValue = "0", required = false) Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "releaseYear", required = false) Integer releaseYear,
            @RequestParam(value = "rating", required = false) Double rating
    ) {
        PaginatedApiResponse<BookDto> response = bookService.getAllBooks(currentPage, pageSize, title, author, releaseYear, rating);

        return ResponseEntity.ok(response);
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
    public ResponseEntity<ApiResponse<BookDto>> updateBook(@PathVariable(value = "id") int id, @RequestBody BookDto bookDto) {
        BookDto updatedBookDto = BookMapper.mapToDto(bookService.updateBook(id, bookDto));
        ApiResponse<BookDto> response = new ApiResponse<>(SUCCESS.toString(), updatedBookDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable(value = "id") int id) {
        bookService.deleteBookById(id);
        ApiResponse<Void> response = new ApiResponse<>(SUCCESS.toString(), null);

        return ResponseEntity.ok(response);
    }
}
