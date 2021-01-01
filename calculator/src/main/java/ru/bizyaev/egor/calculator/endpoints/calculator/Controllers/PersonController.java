package ru.bizyaev.egor.calculator.endpoints.calculator.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.PersonEntity;
import ru.bizyaev.egor.calculator.endpoints.calculator.Services.PersonService;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        return ResponseEntity.ok().body(personService.getAllPersons());
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable long id) {
        return ResponseEntity.ok().body(personService.getPersonById(id));
    }

    @PostMapping("/persons")
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity personEntity) {
        return ResponseEntity.ok().body(this.personService.createPerson(personEntity));
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable long id, @RequestBody PersonEntity personEntity) {
        personEntity.setId(id);
        return ResponseEntity.ok().body(this.personService.updatePerson(personEntity));
    }

    @DeleteMapping("/persons/{id}")
    public HttpStatus deletePerson(@PathVariable long id) {
        this.personService.deletePerson(id);
        return HttpStatus.OK;
    }
}
