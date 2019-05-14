package ru.kinolink.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinolink.service.model.Country;

@Repository
public interface CountryDAO extends JpaRepository<Country, Integer> {
}
