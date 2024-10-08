package com.truksinas.bookApi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class FailApiResponse {
    private String status;
    private Map<String, String> data;
}