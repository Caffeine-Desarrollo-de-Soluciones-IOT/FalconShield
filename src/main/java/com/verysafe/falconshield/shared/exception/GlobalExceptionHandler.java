package com.verysafe.falconshield.shared.exception;

import com.verysafe.falconshield.shared.exception.dto.ErrorDetailsResponse;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorDetailsResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {
        var errorDetails = new ErrorDetailsResponse(
                exception.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now()
        );

        return new ApiResponse<>("El recurso al que intentas acceder no existe", false, errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ApiResponse<List<String>> handleValidationErrors(
            MethodArgumentNotValidException ex,
            WebRequest request
    ) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> {
                    var fieldName = fieldError.getField();
                    var errorMessage = fieldError.getDefaultMessage();
                    return String.format("%s: %s", fieldName, errorMessage);
                })
                .toList();

        return new ApiResponse<>("Validaciones de entrada fallidas", false, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<ErrorDetailsResponse> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) {
        var errorDetails = new ErrorDetailsResponse(
                exception.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now()
        );

        return new ApiResponse<>("Ha ocurrido un error inesperado", false, errorDetails);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    public ApiResponse<ErrorDetailsResponse> handleMultipartMaxSizeException(
            MaxUploadSizeExceededException exception,
            WebRequest webRequest
    ) {
        var errorDetails = new ErrorDetailsResponse(
                exception.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now()
        );

        return new ApiResponse<>("El archivo excede el tamaño máximo permitido (8mb)", false, errorDetails);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<ErrorDetailsResponse>> handleAppException(
            CustomException ex,
            WebRequest webRequest
    ) {
        var errorDetails = new ErrorDetailsResponse(
                ex.getDetails(),
                webRequest.getDescription(false),
                LocalDateTime.now()
        );

        var response = new ApiResponse<>(ex.getMessage(), false, errorDetails);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}
