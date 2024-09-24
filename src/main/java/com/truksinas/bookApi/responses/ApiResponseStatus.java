package com.truksinas.bookApi.responses;

public enum ApiResponseStatus {
    SUCCESS,
    FAIL,
    ERROR;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
