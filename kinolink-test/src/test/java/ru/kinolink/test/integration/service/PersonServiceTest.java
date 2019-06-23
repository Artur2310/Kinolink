package ru.kinolink.test.integration.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.model.Person;
import ru.kinolink.service.model.builder.MovieBuilder;
import ru.kinolink.service.repository.PersonDAO;
import ru.kinolink.service.service.MovieService;
import ru.kinolink.service.service.PersonService;
import ru.kinolink.service.service.impl.PersonServiceImpl;
import ru.kinolink.test.integration.TestAppConfig;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration( classes = {TestAppConfig.class})
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    PersonDAO personDAO;

    @Autowired
    MovieService movieService;

    @Test
    @Transactional
    public void getMatchMovies() {
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

        Movie four = MovieBuilder.newBuilder()
                .setTitle("Four")
                .setDescription("Test ...")
                .setReleaseDate(new Date())
                .build();

        Movie five = MovieBuilder.newBuilder()
                .setTitle("Five")
                .setDescription("Test ...")
                .setReleaseDate(new Date())
                .build();

        movieService.saveAll(Arrays.asList(one, two, three, four, five));

        Person personOne = new Person();
        personOne.setName("one");
        personOne.setActorOfMovies(new HashSet<>(Arrays.asList(one, two, four)));
        personOne.setDirectorOfMovies(new HashSet<>(Arrays.asList(two, three)));

        Person personTwo = new Person();
        personTwo.setName("two");
        personTwo.setActorOfMovies(new HashSet<>(Arrays.asList(three, four)));
        personTwo.setDirectorOfMovies(new HashSet<>(Arrays.asList(four, five)));

        Exception exception = null;

        int idOne = personService.add(personOne).getId();
        int idTwo = personService.add(personTwo).getId();

        personOne = personService.get(idOne);
        personTwo = personService.get(idTwo);

        List<Movie> movies = personService.getMatch(personOne.getId(), personTwo.getId());

        assertNull(exception);
        assertEquals(movies.size() ,2);

    }

    @Test
    public void updatePersonTest(){
        Person person = new Person();
        person.setName("John");

        long countPerson = personDAO.count();

        personDAO.save(person);

        person.setName("Rayan");
        personDAO.save(person);

        assertEquals(personDAO.findAll().size(),++countPerson);
        assertEquals(personDAO.findAll().get(0).getName(),"Rayan");

    }

}
