package com.truksinas.bookApi.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookFilterDto {
    @Min(1)
    private Integer currentPage = 1;

    @Min(1)
    @Max(100)
    private Integer pageSize = 10;

    @Size(max = 100)
    private String title;

    @Size(max = 100)
    private String author;

    @Min(1500)
    @Max(2100)
    private Integer releaseYear;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double higherOrEqualThanRating;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double lowerOrEqualThanRating;
}