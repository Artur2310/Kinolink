package ru.kinolink.web.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kinolink.web.model.Country;
import ru.kinolink.web.webapp.Url;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        model.addObject("search", search);
        model.addObject("sort", sort);
        model.addObject("countryList", countryList);
        model.addObject("countryId", countryId);
        model.setViewName("admin/admin_page_movies");
        return model;
    }
}
