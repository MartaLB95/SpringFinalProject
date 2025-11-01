package com.tokio.demo.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component

public class FilmBatchRunner implements CommandLineRunner {

    private JobLauncher launcher;
    private Job filmJob;

    public FilmBatchRunner(JobLauncher launcher, Job filmJob){
        this.launcher=launcher;
        this.filmJob=filmJob;
    }

    @Override
    public void run(String... args) {
       try{ // Crear parámetros para el job
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        // Ejecutar el Job
        JobExecution execution = launcher.run(filmJob, params);

        // Mostrar resultado
        System.out.println("Estado del job: " + execution.getStatus());
       } catch (Exception e){
           e.printStackTrace();
       }

    }
}
