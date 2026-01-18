package com.tokio.demo.exception;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

/**This exception handler is used for the whole website except for the API Rest. When there is an exception, we see the error Thymeleaf page that we created with
 * the specific error code and message
 *
 */
@ControllerAdvice
public class GlobalWebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalWebExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleBadRequest(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorCode", 400);
        model.addAttribute("errorMessage", "Invalid request.");
        logger.warn("Bad request", ex);
        return "error/error";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleNotFound(EntityNotFoundException ex, Model model) {
        model.addAttribute("errorCode", 404);
        model.addAttribute("errorMessage", ex.getMessage());
        logger.warn("Entity not found", ex);
        return "error/error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessError(AccessDeniedException ex, Model model) {
        model.addAttribute("errorCode", 403);
        model.addAttribute("errorMessage", ex.getMessage());
        logger.warn("Access denied", ex);
        return "error/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleOtherExceptions(Exception ex, Model model) {
        model.addAttribute("errorCode", 500);
        model.addAttribute("errorMessage", ex.getMessage());
        logger.error("Unexpected error", ex);
        return "error/error";
    }
}
