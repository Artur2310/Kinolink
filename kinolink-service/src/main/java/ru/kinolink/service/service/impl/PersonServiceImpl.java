package ru.kinolink.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kinolink.service.repository.PersonDAO;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.model.Person;
import ru.kinolink.service.service.PersonService;


import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service("personService")
@Transactional(Transactional.TxType.REQUIRED)
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonDAO personDAO;

    /**
     * Добавитб человека в базу данных
     *
     * @param person
     * @return {@literal true} если усешно добавлен, {@literal false} иначе.
     */
    @Override
    public Person add(Person person) {
        return (person != null) ?
                Optional.of(personDAO.save(person)).map(m -> {
                    logger.info("Perosn with name - " + person.getName() + " added.");
                    return m;
                }).orElseGet(() -> {
                    logger.error("Perosn not added. PersonDAO return NULL");
                    return null;
                })
                : null;
    }


    @Override
    public Person get(int id) {

        if (id < 0) {
            logger.error("Value 'id = " + id + "' less than 0");
            return null;
        }

        return Optional.ofNullable(personDAO.getOne(id))
                .orElseGet(() -> {
                    logger.error("Person with id = " + id + " not found");
                    return null;
                });

    }

    @Override
    public boolean saveAll(Iterable<Person> list) {
        return Optional.of(list).map(m -> {
            personDAO.saveAll(list);
            return true;
        }).orElseGet(() -> {
            logger.error("The list to add is null");
            return false;
        });
    }

    @Override
    public long count() {
        return personDAO.count();
    }

    /**
     * Get 20 persons sorted by rating
     */
    @Override
    public List<Person> getListTop20(String name) {

        List<Person> list = null;

        if (name != null) {
            Pageable pageable = PageRequest.of(0, 20, Sort.by("rating").descending());
            list = personDAO.findAllByName(name, pageable).getContent();
        }

        return list;
    }

    /**
     * Get persons for page
     */
    @Override
    public Page<Person> getPage(String name, int currentPage) {

        if (name != null && currentPage > 0) {
            Pageable pageable = PageRequest.of(currentPage, 50, Sort.by("rating").descending());
            Page<Person> page = personDAO.findAllByName(name, pageable);
            return page;
        }

        return null;
    }

    /**
     * Get matching movies for two persons
     */
    @Override
    public List<Movie> getMatch(int one, int two) {

        Person personOne = personDAO.getOne(one);
        Person personTwo = personDAO.getOne(two);

        List<Movie> movies = new ArrayList<>();

        if (personOne != null) {
            movies.addAll(personOne.getAllMovies());
        }
        if (personTwo != null) {
            movies.addAll(personTwo.getAllMovies());
        }

        return movies.isEmpty()
                ? null
                : movies.stream()
                .filter(m -> Collections.frequency(movies, m) > 1)
                .sorted(Comparator.comparing(Movie::getReleaseDate).reversed())
                .distinct()
                .collect(Collectors.toList());
    }
}
