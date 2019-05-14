package ru.kinolink.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Genre of the movie
 *
 * @author Bocharow Artur
 */
@Entity
@Table(name = "genre")
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Integer id;

    @Column(name = "GENRE_TITLE")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "genre_movie", joinColumns = @JoinColumn(name = "genre_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new HashSet<Movie>();

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                Objects.equals(title, genre.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }
}
