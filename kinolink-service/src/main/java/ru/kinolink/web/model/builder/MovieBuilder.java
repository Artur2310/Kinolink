package ru.kinolink.web.model.builder;

import ru.kinolink.web.model.Movie;

import java.util.Date;

public class MovieBuilder extends Movie {

    private MovieBuilder() {
    }

    public static Builder newBuilder() {

        return new MovieBuilder().new Builder();

    }

    public class Builder {

        Movie movie;

        private Builder() {
            movie = new Movie();
        }

        public Builder setId(int id) {
            movie.setId(id);
            return this;
        }

        public Builder setTitle(String title) {
            movie.setTitle(title);
            return this;
        }

        public Builder setAlterTitle(String alterTitle) {
            movie.setAlternativeTitle(alterTitle);
            return this;
        }

        public Builder setDescription(String description) {
            movie.setDescription(description);
            return this;
        }

        public Builder setImdb(float imdb) {
            movie.setImdb(imdb);
            return this;
        }

        public Builder setImdbLink(String imdbLink) {
            movie.setImdbLink(imdbLink);
            return this;
        }

        public Builder setPicturePath(String path) {
            movie.setPicturePath(path);
            return this;
        }

        public Builder setReleaseDate(Date date) {
            movie.setReleaseDate(date);
            return this;
        }

        public Builder setTrailer(String trailer) {
            movie.setTrailer(trailer);
            return this;
        }

        public Builder setUrl(String url) {
            movie.setUrl(url);
            return this;
        }

        public Movie build() {
            return movie;
        }

    }
}
