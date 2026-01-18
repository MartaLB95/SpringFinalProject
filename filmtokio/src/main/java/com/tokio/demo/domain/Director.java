package com.tokio.demo.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity

/**Discriminatory Value to specify that this is a subclass of Artist*/
@DiscriminatorValue("Director")
public class Director extends Artist {

    @OneToMany(mappedBy = "director")
    private Set<Film> films;
}
