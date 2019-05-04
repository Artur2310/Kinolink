package ru.kinolink.web.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import ru.kinolink.web.webapp.spring.AppConfig;

@SpringBootApplication
@Import(AppConfig.class)
public class WebApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApp.class, args);
    }
}
