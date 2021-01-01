package ru.bizyaev.egor.calculator.endpoints.calculator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.PersonEntity;
import ru.bizyaev.egor.calculator.endpoints.calculator.Exceptions.ResourceNotFoundException;
import ru.bizyaev.egor.calculator.endpoints.calculator.Repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public PersonEntity createPerson(PersonEntity person) {
        return personRepository.save(person);
    }

    @Override
    public PersonEntity updatePerson(PersonEntity person) {
        Optional<PersonEntity> personDb = this.personRepository.findById(person.getId());

        if (personDb.isPresent()) {
            PersonEntity personUpdate = personDb.get();
            personUpdate.setId(person.getId());
            personUpdate.setLogin(person.getLogin());
            personRepository.save(personUpdate);
            return personUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id: " + person.getId());
        }
    }

    @Override
    public List<PersonEntity> getAllPersons() {
        return this.personRepository.findAll();
    }

    @Override
    public PersonEntity getPersonById(long personId) {

        Optional<PersonEntity> personDb = this.personRepository.findById(personId);

        if (personDb.isPresent()) {
            return personDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id: " + personId);
        }
    }

    @Override
    public void deletePerson(long personId) {
        Optional<PersonEntity> personDb = this.personRepository.findById(personId);

        if (personDb.isPresent()) {
            this.personRepository.delete(personDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id: " + personId);
        }
    }
}
