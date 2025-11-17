package com.tokio.demo.exception;

import com.tokio.demo.controller.RatingRestController;
import com.tokio.demo.dto.api.ApiErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {RatingRestController.class})
public class GlobalRestExceptionHandler{

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorDto> handleAccessDenied(AccessDeniedException ex) {
        ApiErrorDto error = new ApiErrorDto(403, "Access denied: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleBookNotFound(RatingNotFoundException ex) {
        ApiErrorDto error = new ApiErrorDto(404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handleGeneric(Exception ex) {
       ApiErrorDto error = new ApiErrorDto(500, "Unexpected error: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
