package com.truksinas.bookApi.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookFilterDto {
    @Min(0)
    private Integer currentPage = 0;

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

    @Min(0)
    @Max(5)
    private Integer rating;
}