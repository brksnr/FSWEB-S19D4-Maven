package com.workintech.s19d1.entity;

import aj.org.objectweb.asm.ModuleVisitor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="actor", schema="fsweb")
public class Actor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    @NotNull
    private String firstName;
    @NotNull
    @Column(name="last_name")
    private String lastName;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH},fetch= FetchType.EAGER)
    @JoinTable(name="movie_actor",schema = "fsweb",
    joinColumns = @JoinColumn(name="actor_id"),
    inverseJoinColumns = @JoinColumn(name="movie_id"))

    private List<Movie> movies;

    public void addMovie(Movie movie){
        if (movies == null){
            movies = new ArrayList<>();
        }
        movies.add(movie);
    }

}
