package com.tokio.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@Table(name="users")
@Entity
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)


    @Column (name="id", nullable = false)
    private Long id;

    @Column (name="username", nullable = false, unique = true)
    private String username;

    @Column (name="password", nullable = false)
    private String password;

    /**Tiene que coincider con la contraseña anterior, pero creo que esto se consigue a través del servicio*/
    @Column (name="passwordBis", nullable=false)
    private String passwordBis;

    @Column (name="email", nullable = false, unique = true)
    private String email;

    @Column (name="name", nullable = false)
    private String name;

    @Column (name="surname", nullable = false)
    private String surname;

    @Column (name="dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @OneToMany (mappedBy="user")
    private Set<Rating> ratings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="user_role", joinColumns=
            @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
            private Set<Role> roles;

}
