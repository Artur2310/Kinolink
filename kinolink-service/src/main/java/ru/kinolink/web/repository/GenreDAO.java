package ru.kinolink.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinolink.web.model.Genre;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer> {
}
