package com.truksinas.bookApi.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ReviewFilterDto {
    @Min(1)
    private Integer currentPage = 1;

    @Min(1)
    @Max(100)
    private Integer pageSize = 10;

    @Min(1)
    @Max(5)
    private Integer stars;
}
