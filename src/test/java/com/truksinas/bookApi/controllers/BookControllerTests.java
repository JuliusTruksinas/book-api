package com.truksinas.bookApi.controllers;

import com.truksinas.bookApi.dtos.BookDto;
import com.truksinas.bookApi.entities.BookEntity;
import com.truksinas.bookApi.responses.PaginatedApiResponse;
import com.truksinas.bookApi.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetBooks() throws Exception {
        BookDto bookDto = new BookDto(1, "The Great Gatsby", "F. Scott Fitzgerald", 1925, 10.99, 4.5);
        PaginatedApiResponse<BookDto> response = new PaginatedApiResponse<>("success", Collections.singletonList(bookDto), 1, 1, 1);

        Mockito.when(bookService.getAllBooks(anyInt(), anyInt(), any(), any(), any(), any(), any()))
                .thenReturn(response);

        mockMvc.perform(get("/books")
                        .param("title", "The Great Gatsby")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"success\",\"data\":[{\"id\":1,\"title\":\"The Great Gatsby\",\"author\":\"F. Scott Fitzgerald\",\"releaseYear\":1925,\"price\":10.99,\"rating\":4.5}],\"currentPage\":1,\"totalPages\":1,\"totalItems\":1}"));
    }

    @Test
    public void testGetBookById() throws Exception {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("The Great Gatsby");
        bookEntity.setAuthor("F. Scott Fitzgerald");
        bookEntity.setReleaseYear(1925);
        bookEntity.setPrice(10.99);
        bookEntity.setRating(4.5);

        Mockito.when(bookService.getBookById(1)).thenReturn(bookEntity);

        mockMvc.perform(get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"success\",\"data\":{\"id\":1,\"title\":\"The Great Gatsby\",\"author\":\"F. Scott Fitzgerald\",\"releaseYear\":1925,\"price\":10.99,\"rating\":4.5}}"));
    }

    @Test
    public void testCreateBook() throws Exception {
        BookDto bookDto = new BookDto(null, "1984", "George Orwell", 1949, 15.99, 4.8);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(2);
        bookEntity.setTitle("1984");
        bookEntity.setAuthor("George Orwell");
        bookEntity.setReleaseYear(1949);
        bookEntity.setPrice(15.99);
        bookEntity.setRating(4.8);

        Mockito.when(bookService.createBook(Mockito.any(BookDto.class))).thenReturn(bookEntity);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"1984\",\"author\":\"George Orwell\",\"releaseYear\":1949,\"price\":15.99,\"rating\":4.8}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"status\":\"success\",\"data\":{\"id\":2,\"title\":\"1984\",\"author\":\"George Orwell\",\"releaseYear\":1949,\"price\":15.99,\"rating\":4.8}}"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookDto bookDto = new BookDto(1, "1984", "George Orwell", 1949, 15.99, 4.8);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setTitle("1984");
        bookEntity.setAuthor("George Orwell");
        bookEntity.setReleaseYear(1949);
        bookEntity.setPrice(15.99);
        bookEntity.setRating(4.8);

        Mockito.when(bookService.updateBook(Mockito.anyInt(), Mockito.any(BookDto.class))).thenReturn(bookEntity);

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"1984\",\"author\":\"George Orwell\",\"releaseYear\":1949,\"price\":15.99,\"rating\":4.8}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"status\":\"success\",\"data\":{\"id\":1,\"title\":\"1984\",\"author\":\"George Orwell\",\"releaseYear\":1949,\"price\":15.99,\"rating\":4.8}}"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookService).deleteBookById(1);

        mockMvc.perform(delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"success\",\"data\":null}"));
    }

    @Test
    public void testCreateBookValidationError() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"\",\"author\":\"\",\"releaseYear\":1000,\"price\":-10,\"rating\":4.8}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.title").value("Title is mandatory"))
                .andExpect(jsonPath("$.data.author").value("Author is mandatory"))
                .andExpect(jsonPath("$.data.releaseYear").value("Release year must be after 1500"))
                .andExpect(jsonPath("$.data.price").value("Price must be positive"));
    }


    @Test
    public void testUpdateBookValidationError() throws Exception {
        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"\",\"author\":\"\",\"releaseYear\":1000,\"price\":-10,\"rating\":4.8}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.title").value("Title is mandatory"))
                .andExpect(jsonPath("$.data.author").value("Author is mandatory"))
                .andExpect(jsonPath("$.data.releaseYear").value("Release year must be after 1500"))
                .andExpect(jsonPath("$.data.price").value("Price must be positive"));
    }

}