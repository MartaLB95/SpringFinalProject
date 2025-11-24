package com.tokio.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ratings")
public class Rating {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column (name="id", nullable = false)
    private Long id;

    //LocalDateTime para que guarde fecha y hora
    private LocalDateTime createdAt;

    @Column (name="score", nullable = false)
    private int score;
    //Tengo que ver cómo conecto esto

    @ManyToOne
    @JsonBackReference
    @JoinColumn (name="filmId", nullable = false)
    private Film film;


    @ManyToOne
    @JsonBackReference
    @JoinColumn (name="userId", nullable = false)
    private User user;


}
