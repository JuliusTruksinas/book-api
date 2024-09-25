package com.truksinas.bookApi.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookDto {
    private int id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 100, message = "Author must be less than 100 characters")
    private String author;

    @NotNull(message = "Release year is mandatory")
    @Min(value = 1500, message = "Release year must be after 1500")
    @Max(value = 2100, message = "Release year must be before 2100")
    private int releaseYear;

    @Positive(message = "Price must be positive")
    private double price;
}
