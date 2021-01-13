package ru.bizyaev.egor.calculator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bizyaev.egor.calculator.Entities.PersonEntity;
import ru.bizyaev.egor.calculator.Repositories.PersonRepository;
import ru.bizyaev.egor.calculator.Exceptions.PersonNotFoundException;

import java.util.List;

@Service
@Transactional
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonEntity> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public PersonEntity getPerson(String login) throws PersonNotFoundException {
        PersonEntity personDb = getAllPersons().stream()
                     .filter(p -> p.getLogin().equals(login))
                     .findFirst()
                     .orElse(null);

        if (personDb != null) {
            return personDb;
        } else {
            throw new PersonNotFoundException("Not found User with login '" + login + "'");
        }
    }

    @Override
    public boolean findByUsername(String login) {
        return getAllPersons().stream()
               .anyMatch(x -> x.getLogin().equals(login));
    }
}
