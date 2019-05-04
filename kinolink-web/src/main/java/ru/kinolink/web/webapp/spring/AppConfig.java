package ru.kinolink.web.webapp.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    String name(){
        return "Artur";
    }
}
