package ru.kinolink.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kinolink.service.dto.PageViewListMovie;
import ru.kinolink.service.repository.MovieDAO;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.service.MovieService;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Сервис класс дя взаимодействия с таблицей movie через MovieDAO
 *
 * @author Bocharow Artur
 */
@Service("movieService")
@Transactional(Transactional.TxType.REQUIRED)
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    MovieDAO movieDAO;

    public MovieServiceImpl() {
        System.out.println("Create MovieService");
    }

    /**
     * Добавить фильм в базу данных
     *
     * @param movie
     * @return {@literal true} если фильм усешно добавлен, {@literal false} иначе.
     */
    @Override
    public Movie add(Movie movie) {
        return (movie != null) ?
                Optional.of(movieDAO.save(movie)).map(m -> {
                    logger.info("Movie with title - " + movie.getTitle() + " added.");
                    return m;
                }).orElseGet(() -> {
                    logger.error("Movie not added.");
                    return null;
                })
                : null;
    }


    /**
     * Получить фильм по id
     *
     * @param id
     * @return Сущность фильма с данным id, или null.
     */
    @Override
    public Movie get(int id) {
        return (id < 0) ? null : movieDAO.findById(id)
                .orElseGet(() -> {
                    logger.error("Movie with id = " + id + " not found");
                    return null;
                });

    }

    /**
     * Получить список фильмов для отдельной страницы
     *
     * @param pvlMovie содержит информацию из тела запроса для создания списка
     * @return pageViewListMovie модель страницы.
     */
    @Override
    public PageViewListMovie getList(PageViewListMovie pvlMovie) {

        Pageable pageable = PageRequest.of(pvlMovie.getCurrentPage(), pvlMovie.getLimit(), Sort.by(pvlMovie.getSort()));
        Page<Movie> page = null;
        if (pvlMovie.getSearch() != null) {
            page = movieDAO.findAllByTitle(pvlMovie.getSearch(), pageable);

        } else if (pvlMovie.getGenreId() != null && pvlMovie.getCountryId() != null) {
            page = movieDAO.findAllWithGenreAndCountry(pvlMovie.getGenreId(), pvlMovie.getCountryId(), pageable);

        } else if (pvlMovie.getCountryId() != null) {
            page = movieDAO.findAllWithCountry(pvlMovie.getCountryId(), pageable);

        } else if (pvlMovie.getGenreId() != null) {
            page = movieDAO.findAllWithGenre(pvlMovie.getGenreId(), pageable);

        } else {
            page = movieDAO.findAll(pageable);
        }

        pvlMovie.setMovies(page.getContent());
        pvlMovie.setCurrentPage(page.getNumber());
        pvlMovie.setTotalElements(pvlMovie.getTotalElements());
        pvlMovie.setTotalPages(page.getTotalPages());
        return pvlMovie;
    }

    /**
     * Получить список из 20 фильмов, упорядоченных по рейтингу IMDB
     *
     * @return List<Movie>
     */
    @Override
    public List<Movie> getList() {
        return movieDAO.findTop20ByOrderByImdbDesc();
    }

    @Override
    public void remove(int id) {

        if (id < 0) {
            logger.error("Value of id is not valid");
            return;
        } else {
            movieDAO.deleteById(id);
        }

    }

    @Override
    public boolean saveAll(Iterable<Movie> list) {
        return Optional.of(list).map(m -> {
            movieDAO.saveAll(list);
            return true;
        }).orElseGet(() -> {
            logger.error("The list to add is null");
            return false;
        });
    }

    @Override
    public long count() {
        return movieDAO.count();
    }
}
