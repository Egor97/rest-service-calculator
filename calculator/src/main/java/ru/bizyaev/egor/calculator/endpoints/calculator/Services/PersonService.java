package ru.bizyaev.egor.calculator.endpoints.calculator.Services;

import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.PersonEntity;

import java.util.List;

public interface PersonService {
    PersonEntity createPerson(PersonEntity person);
    PersonEntity updatePerson(PersonEntity person);
    List<PersonEntity> getAllPersons();
    PersonEntity getPersonById(long personId);
    void deletePerson(long personId);
}
