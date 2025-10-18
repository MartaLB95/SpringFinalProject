package com.tokio.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table (name="films")

public class Film {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private Long id;

    @Column (name="title", nullable=false)
    private String title;

    /**He podido indicar el número de dígitos con las anotaciones min y max, sin necesidad de implementarlo en el servicio*/

    @Column (name="releaseDate", nullable=false)
   // @Min(1)
   // @Max(9999)
    private int releaseDate;

    /**Mirar cómo conectar esto bien*/
    @ManyToMany
    @JoinTable(
            name="film_actor",
            joinColumns= @JoinColumn (name="film_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="actor_id", nullable = false)
    )
    private Set<Actor> actors;

    @ManyToOne
    @JoinColumn(name="director_id", nullable=false)
    private Director director;

    @Column(name="poster", nullable = false)
    private String poster;

    @OneToMany (mappedBy = "film")
    private Set<Rating> ratings;

}
