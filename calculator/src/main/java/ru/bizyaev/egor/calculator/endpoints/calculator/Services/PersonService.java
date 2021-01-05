package ru.bizyaev.egor.calculator.endpoints.calculator.Services;

import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.PersonEntity;

import java.util.List;

public interface PersonService {
    List<PersonEntity> getAllPersons();
    PersonEntity getPerson(String login);
}
