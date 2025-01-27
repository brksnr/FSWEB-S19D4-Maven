package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="movie", schema="fsweb")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotNull()
    private String name;

    @Column(name="director_name")
    @NotNull
    private String directorName;

    @Column(name="rating")
    private Integer rating;

    @Column(name="release_date")
    private LocalDate release_date;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH},fetch= FetchType.EAGER)
    @JoinTable(name="movie_actor",schema = "fsweb",
            inverseJoinColumns = @JoinColumn(name="actor_id"),
            joinColumns = @JoinColumn(name="movie_id"))

    private List<Actor> actors;

    public void addActor(Actor actor){
        if(actors == null){
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }

    public LocalDate getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(LocalDate release_date) {
        this.release_date = release_date;
    }
}
