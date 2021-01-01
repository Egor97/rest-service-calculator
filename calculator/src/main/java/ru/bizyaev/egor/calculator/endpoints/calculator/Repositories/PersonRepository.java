package ru.bizyaev.egor.calculator.endpoints.calculator.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
