package com.truksinas.bookApi.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedApiResponse<T> {
    private String status;
    private List<T> data;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalItems;
}