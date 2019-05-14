package ru.kinolink.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinolink.web.model.Country;

@Repository
public interface CountryDAO extends JpaRepository<Country, Integer> {
}
