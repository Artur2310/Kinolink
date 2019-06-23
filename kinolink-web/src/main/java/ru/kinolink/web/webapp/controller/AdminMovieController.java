package ru.kinolink.web.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kinolink.service.dto.PageViewListMovie;
import ru.kinolink.service.model.Country;
import ru.kinolink.service.model.Genre;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.model.builder.MovieBuilder;
import ru.kinolink.service.service.MovieService;
import ru.kinolink.service.util.NumberParserUtil;
import ru.kinolink.web.webapp.Url;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class AdminMovieController {

    @Autowired
    MovieService moviesService;

    @GetMapping(value = Url.ADMIN_PAGE_MOVIES)
    public ModelAndView adminPageMovies(ModelAndView model, HttpServletRequest request, HttpServletRequest reuqest) {

        PageViewListMovie pvlMovie = PageViewListMovie.newBuilder()
                .setSearch(request.getParameter("search"))
                .setCurrentPage(NumberParserUtil.parseWithDefault(request.getParameter("page"), -1))
                .setSort(request.getParameter("sort"))
                .setCountryId(NumberParserUtil.parseWithDefault(request.getParameter("countryId"), -1))
                .setGenreId(NumberParserUtil.parseWithDefault(request.getParameter("genreId"), -1))
                .build();

        pvlMovie = moviesService.getList(pvlMovie);

       /* String search = "default";
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
        List<Genre> genreList = Arrays.asList(genreOne, genreTwo);
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
        model.addObject("movieList", movieList);*/
        model.setViewName(AdminController.VIEW_ADMIN_PAGE_MOVIES);
        return model;
    }

    // Admin main page (default movies page)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {

        String search = request.getParameter("search");
        String page = request.getParameter("page");

        PageViewListMovie pvlMovie = PageViewListMovie.newBuilder()
                .setSearch(request.getParameter("search"))
                .setCurrentPage(NumberParserUtil.parseWithDefault(request.getParameter("page"), -1))
                .setSort(request.getParameter("sort"))
                .setCountryId(NumberParserUtil.parseWithDefault(request.getParameter("countryId"), -1))
                .setGenreId(NumberParserUtil.parseWithDefault(request.getParameter("genreId"), -1))
                .build();

       /* Integer pageCount = 0;
        Integer pageInt = (page == null || page.isEmpty()) ? 1 : Integer.parseInt(page);

        List<Movie> movieList;

        if (search != null && !search.isEmpty()) {

            int sizeList = (int) movieService.listMoviesSizeLike(search);
            pageCount = sizeList / 40;

            pageCount = (int) Math.ceil((double) sizeList / 40);

            if (pageInt > pageCount || pageInt < 1)
                pageInt = 1;

            int begin = (pageInt - 1) * 40;
            int size = 40;
            movieList = movieService.getMoviesByTitleLike(search, begin, size);

            model.addObject("search", search);

        } else {
            String sort = request.getParameter("sort");
            String country = request.getParameter("country");
            String genre = request.getParameter("genre");

            Integer countryId = (country == null || country.isEmpty()) ? 0 : Integer.parseInt(country);
            Integer genreId = (genre == null || genre.isEmpty()) ? 0 : Integer.parseInt(genre);

            int sizeList = (int) movieService.listMoviesSize(sort, countryId, genreId);

            pageCount = (int) Math.ceil((double) sizeList / 40);

            if (pageInt > pageCount || pageInt < 1)
                pageInt = 1;

            int begin = (pageInt - 1) * 40;
            int size = 40;

            movieList = movieService.listMovies(sort, false, countryId, genreId, begin, size);

            model.addObject("sort", sort);
            model.addObject("countryId", countryId);
            model.addObject("genreId", genreId);
        }

        model.addObject("page", pageInt);

        model.addObject("pageCount", pageCount);

        List<Genre> genreList = genreService.listGenre("title");

        List<Country> countryList = countryService.listCountry("name");

        model.addObject("genreList", genreList);
        model.addObject("countryList", countryList);
        model.addObject("moviesList", movieList);
        model.setViewName("admin/admin_movies");*/

        return model;
    }
}
