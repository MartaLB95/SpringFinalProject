package com.tokio.demo.batch.configuration;

import com.tokio.demo.batch.model.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Configuration
//Esta anotación habilita el soporte para Spring Batch
@EnableBatchProcessing
public class BatchConfig {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);


    @Bean
    public JdbcCursorItemReader<Film> filmReader(DataSource dataSource) {
        logger.info("Initializing Film JDBC reader with SQL query");
        JdbcCursorItemReader<Film> reader = new JdbcCursorItemReader<>();


        //BDD
        reader.setDataSource(dataSource);
        //Consulta sql
        reader.setSql("Select id, title, release_date, poster FROM films");

        //Mapeo de cada fila a un objeto Film
        reader.setRowMapper((rs, rowNum) -> {
            Film film = new Film();
            film.setId(rs.getLong("id"));
            film.setTitle(rs.getString("title"));
            film.setReleaseDate(rs.getInt("release_date"));
            film.setPoster(rs.getString("poster"));
            return film;
        });
        logger.info("Film reader configured");
        return reader;

    }

    @Bean
    public FlatFileItemWriter<Film> filmWriter() throws IOException {
        FlatFileItemWriter<Film> writer = new FlatFileItemWriter<>();

        //Crea la carpeta output si no existe
        Files.createDirectories(Paths.get("output"));
        logger.info("Output directory created");

        //Archivo de salida
        logger.info("Configuring FlatFileItemWriter for films_export.csv");
        writer.setResource(new FileSystemResource("output/films_export.csv"));

        //Campos que vamos a incluir
        BeanWrapperFieldExtractor<Film> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "title", "releaseDate", "poster"});

        //Para que cada film sea una línea de texto
        DelimitedLineAggregator<Film> aggregator = new DelimitedLineAggregator<>();
        aggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(aggregator);

        logger.info("Film writer configured");

        return writer;
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     ItemReader<Film> filmReader,
                     ItemWriter<Film> filmWriter) {

        logger.info("Step getting ready to create files with chunks of size 10");
        return new StepBuilder("exportFilmsStep", jobRepository)
                .<Film, Film>chunk(10, transactionManager)
                .reader(filmReader)
                .writer(filmWriter)
                .build();
    }

    @Bean
    public Job filmJob(JobRepository jobRepository, Step step) {
        logger.info("Registering job 'filmJob'");
        return new JobBuilder("filmJob", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}


