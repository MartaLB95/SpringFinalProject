package com.tokio.demo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/app-error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        int errorCode = status != null ? Integer.parseInt(status.toString()) : 500;
        String errorMessage;
        switch (errorCode) {
            case 403:
                errorMessage = "You do not have permission to access this page.";
                break;
            case 404:
                errorMessage = "The page you are looking for was not found.";
                break;
            default:
                errorMessage = message != null ? message.toString() : "Unexpected error";
        }
        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);
        return "error/error";
    }
}


