package com.tokio.demo.batch.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Film {
    private Long id;
    private String title;
    private int releaseDate;
    private String poster;
}
