package ru.kinolink.web.integration.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kinolink.web.dto.PageViewListMovie;
import ru.kinolink.web.integration.TestAppConfig;
import ru.kinolink.web.model.Movie;
import ru.kinolink.web.model.builder.MovieBuilder;
import ru.kinolink.web.repository.MovieDAO;
import ru.kinolink.web.service.MovieService;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration( classes = {TestAppConfig.class})
public class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Test
    public void addMovieTest(){
        Movie movieOne = MovieBuilder.newBuilder().
                setTitle("Niko")
                .setImdb(6.5f)
                .setDescription("Test")
                .build();

        assertTrue(movieService.add(movieOne));
        assertTrue(!movieService.add(null));
    }

    @Test
    public void getListMoviesByTitle() {
        Exception exception = null;
        PageViewListMovie pvlMovie = null;
        Page<Movie> pageTwo = null;

        Movie one = MovieBuilder.newBuilder()
                .setTitle("One")
                .setDescription("Test ...")
                .setReleaseDate(new Date())
                .build();

        Movie two = MovieBuilder.newBuilder()
                .setTitle("Two")
                .setDescription("Test ...")
                .setReleaseDate(new Date())
                .build();

        Movie three = MovieBuilder.newBuilder()
                .setTitle("Three")
                .setDescription("Test ...")
                .setReleaseDate(new Date())
                .build();

        movieService.saveAll(Arrays.asList(one, two, three));

        try {
            pvlMovie = PageViewListMovie.newBuilder().setSearch("T").build();
            pvlMovie = movieService.getList(pvlMovie);

        } catch (Exception e) {
            exception = e;
        }

        assertNull(exception);
        assertNotNull(pvlMovie);
        assertEquals(pvlMovie.getMovies().size(), 2);
    }
}
