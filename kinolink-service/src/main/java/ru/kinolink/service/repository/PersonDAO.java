package ru.kinolink.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kinolink.service.model.Person;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer> {

    @Query(value = "SELECT DISTINCT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name,'%'))")
    public Page<Person> findAllByName(@Param("name") String name, Pageable pageable);

}
