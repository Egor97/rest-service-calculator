package ru.bizyaev.egor.calculator.endpoints.calculator.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
