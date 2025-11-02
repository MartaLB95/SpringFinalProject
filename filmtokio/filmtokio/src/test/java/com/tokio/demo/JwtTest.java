package com.tokio.demo;

import com.tokio.demo.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;


@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtUtil jwtUtil;


    @Test
    void testJwt() {
        User user=new User(
                "testUser",
                "testPassword",
                Collections.emptyList()
        );
        final String token = jwtUtil.generateToken(user);
        final boolean validateToken=jwtUtil.validateToken(token, user);
        System.out.println(token+";"+validateToken);
    }

}