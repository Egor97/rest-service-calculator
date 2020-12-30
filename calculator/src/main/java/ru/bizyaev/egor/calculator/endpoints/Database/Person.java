package ru.bizyaev.egor.calculator.endpoints.Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    private String id;
    private String name;
    @Column
    private String email;
}
