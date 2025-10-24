package com.tokio.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="artists")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column (name="id", nullable = false)
    private Long id;

    @Column (name="name", nullable=false)
    private String name;

    @Column (name="lastname", nullable = false)
    private String lastname;

    /**Mirar esto, no sé si debería ser otro tipo de dato al estar en un desplegable (supongo que esto
     * se hace en la interfaz**/
    @Column (name="type", nullable = false)
    private String type;


}
