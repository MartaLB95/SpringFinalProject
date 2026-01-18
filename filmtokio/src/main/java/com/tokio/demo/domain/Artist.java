package com.tokio.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "artists")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/**Here we add the discriminator column that states that this columns will have different types to choose from*/
@DiscriminatorColumn(name = "type")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;


}
