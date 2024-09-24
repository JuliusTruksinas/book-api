package com.truksinas.bookApi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private ApiResponseStatus status;
    private T data;
}
