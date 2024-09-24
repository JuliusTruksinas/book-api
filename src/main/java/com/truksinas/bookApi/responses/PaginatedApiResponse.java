package com.truksinas.bookApi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedApiResponse<T> {
    private String status;
    private List<T> data;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalItems;
}