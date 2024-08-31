package com.verysafe.falconshield.shared.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {
    private String message;
    private boolean isSuccess;
    private T data;

    public ApiResponse() {}

    public ApiResponse(String message, boolean isSuccess, T data) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.data = data;
    }
}
