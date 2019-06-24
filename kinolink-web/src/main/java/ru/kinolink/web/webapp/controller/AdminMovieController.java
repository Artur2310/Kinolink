package ru.kinolink.web.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${upload.dir}")
    String uploadDir;

    @GetMapping(value = Url.ADMIN_PAGE_MOVIES)
    public ModelAndView adminPageMovies(ModelAndView model, HttpServletRequest request, HttpServletRequest reuqest) {

        PageViewListMovie pvlMovie = PageViewListMovie.newBuilder()
                .setSearch(request.getParameter("search"))
                .setCurrentPage(NumberParserUtil.parseWithDefault(request.getParameter("page"), -1))
                .setSort(request.getParameter("sort"))
                .setCountryId(NumberParserUtil.parseWithDefault(request.getParameter("countryId"), -1))
                .setGenreId(NumberParserUtil.parseWithDefault(request.getParameter("genreId"), -1))
                .build();

        if(pvlMovie.getCurrentPage() > 0){
            pvlMovie.setCurrentPage(pvlMovie.getCurrentPage() - 1);
        }

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
        //model.addObject("nextPage", pvlMovie.getNextPage());

        model.addObject("genres", genreDAO.findAll());
        model.addObject("countries", countryDAO.findAll());
        model.addObject("sorts", SortUtil.MovieSort.getList());
        model.addObject("uploadDir", uploadDir);

        model.setViewName(AdminController.VIEW_ADMIN_PAGE_MOVIES);
        return model;
    }

}
