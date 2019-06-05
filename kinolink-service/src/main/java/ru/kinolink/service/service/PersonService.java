package ru.kinolink.service.service;

import org.springframework.data.domain.Page;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.model.Person;

import java.util.List;

public interface PersonService {

    boolean add(Person person);

    Person get(int id);

    long count();

    boolean saveAll(Iterable<Person> list);

    List<Person> getListTop20(String name);

    Page<Person> getPage(String name, int currentPage);

    List<Movie> getMatch(int one, int two);
}
