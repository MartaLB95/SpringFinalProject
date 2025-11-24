package com.tokio.demo.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity

@DiscriminatorValue("Actor")
public class Actor extends Artist {

@ManyToMany
    private Set<Film> films;
}
