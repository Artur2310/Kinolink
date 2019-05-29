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

    @RequestMapping(value = "")
    public String getAdminPage(){
        return "redirect:"+Url.ADMIN_PAGE_MOVIES;
    }

}
