package ru.kinolink.service.service;

import ru.kinolink.service.dto.PageViewListMovie;
import ru.kinolink.service.model.Movie;

import java.util.List;

/**
 * Service interface for access to Movie collection
 *
 * @author Bocharow Artur
 */
public interface MovieService {

    public Movie add(Movie movie);

    public Movie get(int id);

    public PageViewListMovie getList(PageViewListMovie pageViewListMovie);

    public List<Movie> getList();

    public void remove(int id);

    public boolean saveAll(Iterable<Movie> list);

    public long count();
}

