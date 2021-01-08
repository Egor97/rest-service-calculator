package ru.bizyaev.egor.calculator.Services;

import ru.bizyaev.egor.calculator.Entities.PersonEntity;

import java.util.List;

public interface PersonService {
    List<PersonEntity> getAllPersons();
    PersonEntity getPerson(String login);
}
