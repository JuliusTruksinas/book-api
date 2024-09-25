package com.truksinas.bookApi.services;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.exceptions.BookNotFoundException;
import com.truksinas.bookApi.repositories.BookRepository;
import com.truksinas.bookApi.repositories.ReviewRepository;
import com.truksinas.bookApi.responses.PaginatedApiResponse;
import com.truksinas.bookApi.services.impl.BookServiceImpl;
import com.truksinas.bookApi.utils.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class BookServiceImplTests {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBook() {
        BookDto bookDto = new BookDto(null, "Book Title", "Author Name", 2023, 19.99, 4.5);
        BookEntity bookEntity = BookMapper.mapToEntity(bookDto);

        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);

        BookEntity result = bookService.createBook(bookDto);

        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
        verify(bookRepository, times(1)).save(any(BookEntity.class));
    }

    @Test
    public void testGetBookById() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("Book Title");

        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(bookEntity));

        BookEntity result = bookService.getBookById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Book Title", result.getTitle());
    }

    @Test
    public void testGetBookById_NotFound() {
        when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1));
    }

    @Test
    public void testDeleteBookById() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);

        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(bookEntity));
        doNothing().when(bookRepository).delete(any(BookEntity.class));

        bookService.deleteBookById(1);

        verify(bookRepository, times(1)).delete(any(BookEntity.class));
    }

    @Test
    public void testUpdateBook() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("Old Title");

        BookDto bookDto = new BookDto(1, "Updated Title", "Author Name", 2023, 19.99, 4.5);

        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(bookEntity));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);

        BookEntity result = bookService.updateBook(1, bookDto);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(bookRepository, times(1)).save(any(BookEntity.class));
    }

    @Test
    public void testGetAllBooks() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("Book Title");

        Page<BookEntity> bookPage = new PageImpl<>(Collections.singletonList(bookEntity));
        when(bookRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(bookPage);

        PaginatedApiResponse<BookDto> result = bookService.getAllBooks(1, 10, null, null, null, null, null);

        assertNotNull(result);
        assertEquals(1, result.getTotalItems());
        assertEquals("Book Title", result.getData().get(0).getTitle());
    }
}