package com.tokio.demo.controller;

import com.tokio.demo.dto.api.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api")
public class AuthRestController {

   /* @PostMapping (value="/login", consumes="application/json", produces="application/json")
    public ResponseEntity<?> login (@RequestBody JwtRequest authRequest) throws Exception{
        authenticate(authRequest.getUsername(), authRequest.getPassword());
        final String token=jwtUtil.generateToken();
    }*/
}
