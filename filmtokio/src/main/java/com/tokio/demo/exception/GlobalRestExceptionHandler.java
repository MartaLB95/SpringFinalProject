package com.tokio.demo.exception;

import com.tokio.demo.controller.RatingRestController;
import com.tokio.demo.dto.api.ApiErrorDto;
import com.tokio.demo.service.impl.ArtistServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {RatingRestController.class})
public class GlobalRestExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorDto> handleAccessDenied(AccessDeniedException ex) {
        ApiErrorDto error = new ApiErrorDto(403, "Access denied: " + ex.getMessage());
        logger.warn("Access denied:", ex);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleBookNotFound(RatingNotFoundException ex) {
        ApiErrorDto error = new ApiErrorDto(404, ex.getMessage());
        logger.warn("Rating not found:", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handleGeneric(Exception ex) {
       ApiErrorDto error = new ApiErrorDto(500, "Unexpected error: " + ex.getMessage());
        logger.error("500:", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
