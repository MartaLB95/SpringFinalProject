package com.tokio.demo.batch.configuration;

import com.tokio.demo.batch.model.Film;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Configuration
    //Esta anotación habilita el soporte para Spring Batch
    @EnableBatchProcessing
    public class BatchConfig{

        @Bean
        public JdbcCursorItemReader<Film> filmReader(DataSource dataSource) {
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
            return reader;

        }

        @Bean
        public FlatFileItemWriter<Film> filmWriter() throws IOException {
            FlatFileItemWriter<Film> writer = new FlatFileItemWriter<>();

            //Crea la carpeta output si no existe
            Files.createDirectories(Paths.get("output"));

            //Archivo de salida
            writer.setResource(new FileSystemResource("output/films_export.csv"));

            //Campos que vamos a incluir
            BeanWrapperFieldExtractor<Film> fieldExtractor = new BeanWrapperFieldExtractor<>();
            fieldExtractor.setNames(new String[]{"id", "title", "releaseDate", "poster"});

            //Para que cada film sea una línea de texto
            DelimitedLineAggregator<Film> aggregator = new DelimitedLineAggregator<>();
            aggregator.setFieldExtractor(fieldExtractor);
            writer.setLineAggregator(aggregator);

            return writer;
    }
+


    }


