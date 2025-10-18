package com.tokio.demo.controller;

import com.tokio.demo.dto.LoginDTO;
import com.tokio.demo.dto.UserRegisterDTO;
import com.tokio.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("loginDTO", new LoginDTO());
        if (error != null) {
            model.addAttribute("errorMsg", "login.notvalid"); // Thymeleaf traducirá con i18n
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister
            (@Valid @ModelAttribute UserRegisterDTO userRegisterDTO,  BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        }
            if (userServiceImpl.existsByUsername(userRegisterDTO.getUsername())) {
                result.rejectValue("username",  "error.userRegisterDTO", "Username already exists");
                return "register";
            }

            userServiceImpl.save(userRegisterDTO);
            return "redirect:/films";
        }


}

