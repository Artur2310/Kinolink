package ru.kinolink.service.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.kinolink.service.repository.PersonDAO;
import ru.kinolink.service.webapp.spring.AppConfig;

@SpringBootApplication
@Import(AppConfig.class)
public class WebApp {

    @Autowired
    PersonDAO personDAO;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApp.class, args);
    }
}
