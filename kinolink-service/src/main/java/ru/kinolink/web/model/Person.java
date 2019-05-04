package ru.kinolink.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity for a actor or director
 *
 * @author Bocharow Artur
 */
@Entity
@Table(name = "person")
@Getter
@Setter
public class Person implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;

    @Column(name = "URL")
    private String url;

    @Column(name = "PICTURE_PATH")
    private String picturePath;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "RATING", nullable = false, columnDefinition = "int default 0")
    private int rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actor_movie", joinColumns = @JoinColumn(name = "actor_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> actorOfMovies = new HashSet<Movie>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "director_movie", joinColumns = @JoinColumn(name = "director_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> directorOfMovies = new HashSet<Movie>();

    public Person() {
    }

    public Set<Movie> getAllMovies() {
        Set<Movie> allMovies = new HashSet<Movie>(getActorOfMovies());
        allMovies.addAll(getDirectorOfMovies());
        return allMovies;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                rating == person.rating &&
                Objects.equals(url, person.url) &&
                Objects.equals(picturePath, person.picturePath) &&
                Objects.equals(name, person.name) &&
                Objects.equals(actorOfMovies, person.actorOfMovies) &&
                Objects.equals(directorOfMovies, person.directorOfMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, picturePath, name, rating);
    }
}
