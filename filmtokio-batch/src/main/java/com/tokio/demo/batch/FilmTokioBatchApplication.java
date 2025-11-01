package com.tokio.demo.batch;

import com.tokio.demo.batch.configuration.BatchConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(BatchConfig.class)

@SpringBootApplication
public class FilmTokioBatchApplication {


	public static void main(String[] args) {
		SpringApplication.run(FilmTokioBatchApplication.class, args);
	}

}
