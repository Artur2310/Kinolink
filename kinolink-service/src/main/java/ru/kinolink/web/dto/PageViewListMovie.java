package ru.kinolink.web.dto;

import lombok.Getter;
import lombok.Setter;
import ru.kinolink.web.SortUtil;
import ru.kinolink.web.model.Movie;

import java.util.List;

/**
 * DTO для отображения списка фильмов на странице, с учетом сортировки, выбора страны и жанра
 *
 * @author Bocharow Artur
 */
@Getter
@Setter
public class PageViewListMovie {

    private List<Movie> movies;
    private Long totalElements;
    private Integer totalPages;
    private Integer currentPage;
    private String sort;
    private Integer countryId;
    private Integer genreId;
    private Integer limit;
    private String search;

    public static PageViewListMovie.Builder newBuilder() {

        return new PageViewListMovie().new Builder();

    }

    public String getSort() {
        if (sort == null) {
            return "release_date";
        }

        return sort;
    }

    public String getSearch() {

        return search != null ? search.trim() : null;
    }

    public Integer getNextPage() {
        return ++currentPage;
    }

    public class Builder {

        PageViewListMovie pageViewListMovie;

        private Builder() {
            pageViewListMovie = new PageViewListMovie();
        }

        public Builder setMovies(List<Movie> movies) {
            this.pageViewListMovie.setMovies(movies);
            return this;
        }

        public Builder setTotalElements(Long totalElements) {
            this.pageViewListMovie.setTotalElements(totalElements);
            return this;
        }

        public Builder setTotalPage(Integer totalPage) {
            this.pageViewListMovie.setTotalPages(totalPage);
            return this;
        }

        public Builder setCurrentPage(Integer currentPage) {
            this.pageViewListMovie.setCurrentPage(currentPage);
            return this;
        }

        public Builder setSort(String sort) {
            if (sort != null || SortUtil.MovieSort.isValid(sort)) {
                this.pageViewListMovie.setSort(sort);
            } else {
                this.pageViewListMovie.setSort("id");
            }
            return this;
        }

        public Builder setCountryId(Integer countryId) {
            if (countryId < 0) {
                this.pageViewListMovie.setCountryId(null);
            } else {
                this.pageViewListMovie.setCountryId(countryId);
            }
            return this;
        }

        public Builder setGenreId(Integer genreId) {
            if (genreId < 0) {
                this.pageViewListMovie.setGenreId(null);
            } else {
                this.pageViewListMovie.setGenreId(genreId);
            }
            return this;
        }

        public Builder setLimit(Integer limit) {
            if (limit < 0) {
                this.pageViewListMovie.limit = 50;
            } else {
                this.pageViewListMovie.limit = limit;
            }
            return this;
        }

        public Builder setSearch(String search) {
            this.pageViewListMovie.setSearch(search);
            return this;
        }

        public PageViewListMovie build() {
            return this.pageViewListMovie;
        }
    }
}
