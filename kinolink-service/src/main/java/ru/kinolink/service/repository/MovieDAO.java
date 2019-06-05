package ru.kinolink.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kinolink.service.model.Movie;

import java.util.List;

@Repository
public interface MovieDAO extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT DISTINCT m FROM Movie m JOIN m.country c WHERE c.id = :country")
    public Page<Movie> findAllWithCountry(@Param("country") Integer country, Pageable pageable);

    @Query(value = "SELECT DISTINCT m FROM Movie m JOIN m.genre g WHERE g.id = :genre")
    public Page<Movie> findAllWithGenre(@Param("genre") Integer genre, Pageable pageable);

    @Query(value = "SELECT DISTINCT m FROM Movie m JOIN m.genre g  JOIN m.country c WHERE g.id = :genre AND c.id = :country")
    public Page<Movie> findAllWithGenreAndCountry(@Param("genre") Integer genre, @Param("country") Integer country, Pageable pageable);

    @Query(value = "SELECT DISTINCT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title,'%'))")
    public Page<Movie> findAllByTitle(@Param("title") String title, Pageable pageable);

    public List<Movie> findTop20ByOrderByImdbDesc();

}
