package com.truksinas.bookApi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorApiResponse {
    private String status;
    private String message;
}
