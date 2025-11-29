package com.tokio.demo.controller;

import com.tokio.demo.dto.web.LoginDTO;
import com.tokio.demo.dto.web.UserRegisterDTO;
import com.tokio.demo.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
            logger.error("login error");
        }
        logger.info("Login form is shown");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        logger.info("Register form is shown");
        return "register";
    }

    @PostMapping("/register")
    public String postRegister
            (@Valid @ModelAttribute UserRegisterDTO userRegisterDTO,  BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("Register error");
            return "register";
        }
            if (userServiceImpl.existsByUsername(userRegisterDTO.getUsername())) {
                result.rejectValue("username",  "error.userRegisterDTO", "Username already exists");
                logger.error("Username already exists");
                return "register";
            }

            userServiceImpl.save(userRegisterDTO);
            logger.info("user {} registered successfully", userRegisterDTO.getUsername());
            return "redirect:/films";
        }


}

