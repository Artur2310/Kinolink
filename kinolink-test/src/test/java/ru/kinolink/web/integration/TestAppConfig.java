package ru.kinolink.web.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TestDBConfig.class)
public class TestAppConfig {

}
