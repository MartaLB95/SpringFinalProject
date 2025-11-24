package com.tokio.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

@Controller

public class LanguageController {

    private static final Logger logger = LoggerFactory.getLogger(LanguageController.class);

    @GetMapping("/change-lang")
    public String changeLanguage(@RequestParam("lang") String lang,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setLocale(request, response, Locale.forLanguageTag(lang));

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/login");
    }
}
