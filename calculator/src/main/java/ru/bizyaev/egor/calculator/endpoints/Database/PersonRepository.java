package ru.bizyaev.egor.calculator.endpoints.Database;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
}
