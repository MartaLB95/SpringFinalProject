package com.tokio.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "films")

public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "releaseDate", nullable = false)
    private int releaseDate;

    /**
     * Many to many because each actor can be in many films and a film can have many actors
     */
    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "actor_id", nullable = false)
    )
    private Set<Actor> actors;

    /**Many to one because a film has one director and a director can have several films*/
    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @Column(name = "poster", nullable = false)
    private String poster;

    /**One film, many ratings, but each rating for just one film*/
    /**JsonIgnoreProperties to make serialization possible*/
    @OneToMany(mappedBy = "film")
    @JsonIgnoreProperties("rating")
    private Set<Rating> ratings;

}
