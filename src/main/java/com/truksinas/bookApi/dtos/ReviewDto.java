package com.truksinas.bookApi.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewDto {
    private Integer id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Content is mandatory")
    @Size(max = 1000, message = "Content must be less than 1000 characters")
    private String content;

    @NotNull(message = "Stars are mandatory")
    @Min(value = 1, message = "Stars must be at least 1")
    @Max(value = 5, message = "Stars must be at most 5")
    private Integer stars;
}
