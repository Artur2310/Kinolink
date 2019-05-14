package ru.kinolink.service.integration.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kinolink.service.integration.TestAppConfig;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.model.builder.MovieBuilder;
import ru.kinolink.service.repository.MovieDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration( classes = {TestAppConfig.class})
public class MovieServiceTest {

    @Autowired
    MovieDAO movieDAO;

    @Test
    public void addMovieTest(){
        Movie movieOne = MovieBuilder.newBuilder().
                setTitle("Niko")
                .setImdb(6.5f)
                .setDescription("Test")
                .build();
    }
}
