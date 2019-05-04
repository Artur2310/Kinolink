package ru.kinolink.web.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity of the movie
 *
 * @author Bocharow Artur
 */
@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;

    @Column(name = "URL")
    private String url;

    @Column(name = "PICTURE_PATH")
    private String picturePath;

    @Column(name = "TITLE", nullable = false)
    @Length(min = 1)
    private String title;

    @Column(name = "ALTER_TITLE")
    private String alternativeTitle;

    @DecimalMax("10")
    @DecimalMin("0")
    @NumberFormat(style = Style.NUMBER)
    @Column(name = "IMDB")
    private float imdb;

    @Column(name = "IMDB_LINK")
    private String imdbLink;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Column(name = "TRAILER", length = 500)
    private String trailer;

    @Column(name = "RELEASE_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actor_movie", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Person> actors = new HashSet<Person>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "director_movie", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Person> directors = new HashSet<Person>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "country_movie", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> country = new HashSet<Country>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "genre_movie", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genre = new HashSet<Genre>();

    public Movie() {
    }

    public Movie(int id, String picturePath, String title, float imdb, Set<Genre> genre, Date releaseDate, Set<Country> country) {
        super();
        this.id = id;
        this.picturePath = picturePath;
        this.title = title;
        this.imdb = imdb;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.country = country;
    }

    public String getGenreString() {
        String listGenre = "";

        for (Genre tempGenre : genre) {
            listGenre = listGenre.concat(tempGenre.toString() + " ");
        }
        return listGenre;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + imdb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Float.compare(movie.imdb, imdb) == 0 &&
                Objects.equals(url, movie.url) &&
                Objects.equals(picturePath, movie.picturePath) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(alternativeTitle, movie.alternativeTitle) &&
                Objects.equals(imdbLink, movie.imdbLink) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(trailer, movie.trailer) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(actors, movie.actors) &&
                Objects.equals(directors, movie.directors) &&
                Objects.equals(country, movie.country) &&
                Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, picturePath, title, alternativeTitle, imdb, imdbLink, description, trailer, releaseDate, country, genre);
    }

}
