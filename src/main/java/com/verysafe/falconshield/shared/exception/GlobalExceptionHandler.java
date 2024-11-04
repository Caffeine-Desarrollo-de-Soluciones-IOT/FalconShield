package com.verysafe.falconshield.shared.exception;

import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> {
                    var fieldName = fieldError.getField();
                    var errorMessage = fieldError.getDefaultMessage();
                    return String.format("%s: %s", fieldName, errorMessage);
                })
                .toList();

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid input data, please check the errors");
        problemDetail.setTitle("Bad request");
        problemDetail.setProperty("errors", errors);
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource not found");
        return problemDetail;
    }

    @ExceptionHandler(CustomException.class)
    public ProblemDetail handleAppException(CustomException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(ex.getHttpStatus(), ex.getDetails());
        problemDetail.setTitle(ex.getMessage());
        return problemDetail;
    }
}
