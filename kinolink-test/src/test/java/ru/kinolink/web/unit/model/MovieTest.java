package ru.kinolink.web.unit.model;

import org.junit.Test;
import ru.kinolink.web.model.Movie;
import ru.kinolink.web.model.builder.MovieBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieTest {

    @Test
    public void movieNotEqualsTest() {

        Movie movieOne = MovieBuilder.newBuilder()
                            .setId(1)
                            .setTitle("One")
                            .setImdb(6.5f)
                            .build();

        Movie movieTwo = MovieBuilder.newBuilder()
                            .setId(2)
                            .setTitle("Two")
                            .setImdb(8.3f)
                            .build();

        Set<Movie> movies = new HashSet<>(Arrays.asList(movieOne, movieTwo));

        assertTrue(!movieOne.equals(movieTwo));
        assertEquals(movies.size(),2);
    }

    @Test
    public void movieEqualsTest() {

        Movie movieOne = MovieBuilder.newBuilder()
                .setId(1)
                .setTitle("One")
                .setImdb(6.5f)
                .build();

        Movie movieTwo = MovieBuilder.newBuilder()
                .setId(1)
                .setTitle("One")
                .setImdb(6.5f)
                .build();

        Set<Movie> movies = new HashSet<>(Arrays.asList(movieOne, movieTwo));

        assertTrue(movieOne.equals(movieTwo));
        assertEquals(movies.size(),1);
    }
}
