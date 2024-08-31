package com.verysafe.falconshield.shared.exception.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorDetailsResponse {
    private String details;
    private String uri;
    private LocalDateTime timestamp;

    public ErrorDetailsResponse() {}

    public ErrorDetailsResponse(String details, String uri, LocalDateTime timestamp) {
        this.details = details;
        this.uri = uri;
        this.timestamp = timestamp;
    }
}
