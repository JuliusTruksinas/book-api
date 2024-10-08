package com.truksinas.bookApi.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    private Integer id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 100, message = "Author must be less than 100 characters")
    private String author;

    @NotNull(message = "Release year is mandatory")
    @Min(value = 1500, message = "Release year must be after 1500")
    @Max(value = 2100, message = "Release year must be before 2100")
    private Integer releaseYear;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Double price;

    private Double rating;
}
