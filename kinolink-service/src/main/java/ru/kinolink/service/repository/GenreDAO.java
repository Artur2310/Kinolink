package ru.kinolink.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinolink.service.model.Genre;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer> {
}
