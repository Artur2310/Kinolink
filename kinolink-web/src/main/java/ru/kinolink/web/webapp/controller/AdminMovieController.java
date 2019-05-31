package ru.kinolink.web.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kinolink.web.model.Country;
import ru.kinolink.web.model.Genre;
import ru.kinolink.web.model.Movie;
import ru.kinolink.web.model.builder.MovieBuilder;
import ru.kinolink.web.webapp.Url;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class AdminMovieController {

    @GetMapping(value = Url.ADMIN_PAGE_MOVIES)
    public ModelAndView adminPageMovies(ModelAndView model, HttpServletRequest reuqest){
        String search = "default";
        String sort = "imdb";
        Integer countryId = 2;
        Country countryOne = new Country();
        countryOne.setId(1);
        countryOne.setName("Russia");
        Country countryTwo = new Country();
        countryTwo.setId(2);
        countryTwo.setName("USA");
        List<Country> countryList = new ArrayList<>();
        countryList.add(countryOne);
        countryList.add(countryTwo);
        Genre genreOne = new Genre();
        genreOne.setId(1);
        genreOne.setTitle("Боевик");
        Genre genreTwo = new Genre();
        genreTwo.setId(2);
        genreTwo.setTitle("Комедия");
        List<Genre> genreList = Arrays.asList(genreOne,genreTwo);
        Integer genreId = 2;

        Movie one = MovieBuilder.newBuilder()
                .setTitle("One")
                .setDescription("Test ...")
                .setReleaseDate(new Date())
                .build();

        Movie two = MovieBuilder.newBuilder()
                .setTitle("Two")
                .setDescription("Test ...")
                .setReleaseDate(new Date())
                .build();
        List<Movie> movieList = Arrays.asList(one, two);

        model.addObject("search", search);
        model.addObject("sort", sort);
        model.addObject("countryList", countryList);
        model.addObject("countryId", countryId);
        model.addObject("genreList", genreList);
        model.addObject("genreId", genreId);
        model.addObject("movieList", movieList);
        model.setViewName(AdminController.VIEW_ADMIN_PAGE_MOVIES);
        return model;
    }
}
