package ru.kinolink.web.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class,
        ServerConfig.class,
        LocaleConfig.class})
public class AppConfig {


}
