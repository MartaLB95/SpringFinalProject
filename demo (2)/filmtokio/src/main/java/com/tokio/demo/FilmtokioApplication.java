package com.tokio.demo;

import com.tokio.demo.security.TokioUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
public class FilmtokioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmtokioApplication.class, args);
	}

}
