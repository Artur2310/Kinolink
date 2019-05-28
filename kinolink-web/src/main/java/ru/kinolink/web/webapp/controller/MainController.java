package ru.kinolink.web.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

@Controller
public class MainController {

    @GetMapping(value = "/")
    String getTest(){
        return "test";
    }
}
