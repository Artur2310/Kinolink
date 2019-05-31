package ru.kinolink.web.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kinolink.web.webapp.Url;

@Controller
@RequestMapping(value = Url.ADMIN_PAGE)
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    public static final String VIEW_ADMIN_PAGE_MOVIES = "admin/admin_page_movies";

    @RequestMapping(value = "")
    public String getAdminPage(){
        return "redirect:"+Url.ADMIN_PAGE_MOVIES;
    }

}
