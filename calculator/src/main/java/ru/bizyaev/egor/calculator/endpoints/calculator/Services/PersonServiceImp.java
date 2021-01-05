package ru.bizyaev.egor.calculator.endpoints.calculator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.PersonEntity;
import ru.bizyaev.egor.calculator.endpoints.calculator.Exceptions.ResourceNotFoundException;
import ru.bizyaev.egor.calculator.endpoints.calculator.Repositories.PersonRepository;

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
    public PersonEntity getPerson(String login) {
        PersonEntity personDb = getAllPersons().stream()
                    .filter(p -> p.getLogin().equals(login))
                    .findFirst()
                    .orElse(null);

        if (personDb != null) {
            return personDb;
        } else {
            throw new ResourceNotFoundException("Record not found with login: " + login);
        }
    }
}
