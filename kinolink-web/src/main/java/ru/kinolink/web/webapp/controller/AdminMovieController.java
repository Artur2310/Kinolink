package ru.kinolink.web.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kinolink.web.webapp.Url;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminMovieController {

    @GetMapping(value = Url.ADMIN_PAGE_MOVIES)
    public ModelAndView adminPageMovies(ModelAndView model, HttpServletRequest reuqest){
        model.setViewName("admin/admin_page_movies");
        return model;
    }
}
