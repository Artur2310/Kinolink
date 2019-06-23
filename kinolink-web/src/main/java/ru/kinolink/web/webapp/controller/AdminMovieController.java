package ru.kinolink.web.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kinolink.service.SortUtil;
import ru.kinolink.service.dto.PageViewListMovie;
import ru.kinolink.service.repository.CountryDAO;
import ru.kinolink.service.repository.GenreDAO;
import ru.kinolink.service.service.MovieService;
import ru.kinolink.service.util.NumberParserUtil;
import ru.kinolink.web.webapp.Url;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminMovieController {

    @Autowired
    MovieService moviesService;

    @Autowired
    CountryDAO countryDAO;

    @Autowired
    GenreDAO genreDAO;

    @Autowired
    BinderResponse binderResponse;

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

        model.addObject("movies", pvlMovie.getMovies());
        model.addObject("totalElements", pvlMovie.getTotalElements());
        model.addObject("totalPages", pvlMovie.getTotalPages());
        model.addObject("currentPage", pvlMovie.getCurrentPage());
        model.addObject("sort", pvlMovie.getSort());
        model.addObject("countryId", pvlMovie.getCountryId());
        model.addObject("genreId", pvlMovie.getGenreId());
        model.addObject("limit", pvlMovie.getLimit());
        model.addObject("search", pvlMovie.getSearch());
        model.addObject("nextPage", pvlMovie.getNextPage());

        model.addObject("genres", genreDAO.findAll());
        model.addObject("countries", countryDAO.findAll());
        model.addObject("sorts", SortUtil.MovieSort.getList());

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
        model.addObject("genreId", genreId);*/
       // model.addObject("movies", movieList);
        model.setViewName(AdminController.VIEW_ADMIN_PAGE_MOVIES);
        return model;
    }

}
