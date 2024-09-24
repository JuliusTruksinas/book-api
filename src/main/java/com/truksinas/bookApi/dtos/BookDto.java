package com.truksinas.bookApi.dtos;

import lombok.Data;

@Data
public class BookDto {
    private int id;
    private String title;
    private String author;
    private int releaseYear;
    private double price;
}
