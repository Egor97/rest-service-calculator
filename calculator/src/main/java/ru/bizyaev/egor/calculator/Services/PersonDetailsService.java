package ru.bizyaev.egor.calculator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.bizyaev.egor.calculator.Entities.PersonEntity;
import ru.bizyaev.egor.calculator.Entities.enams.UserRoleEnum;
import ru.bizyaev.egor.calculator.Exceptions.PersonNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonService personService;

    @Autowired
    public PersonDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws PersonNotFoundException {
        PersonEntity personEntity = personService.getPerson(login);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));
        assert personEntity != null;
        return new User(personEntity.getLogin(), personEntity.getPassword(), roles);
    }
}