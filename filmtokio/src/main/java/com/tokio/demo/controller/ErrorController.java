package com.tokio.demo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @Autowired
    private MessageSource messageSource;


    @GetMapping("/app-error")
    public String handleError(HttpServletRequest request, Model model, Locale locale) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        int errorCode = status != null ? Integer.parseInt(status.toString()) : 500;

        String errorMessage = messageSource.getMessage("error." + errorCode, null, locale);

        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);
        logger.info("Error {} occurred: {}", errorCode, errorMessage);
        return "error/error";
    }
}


