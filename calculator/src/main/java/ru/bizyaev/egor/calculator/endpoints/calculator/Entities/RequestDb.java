package ru.bizyaev.egor.calculator.endpoints.calculator.Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request")
public class RequestDb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    @CreationTimestamp
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login", nullable = false)
    private PersonEntity personEntity;

    @Column
    private boolean fromCache;

    @Column
    private boolean computation;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    public boolean isFromCache() {
        return fromCache;
    }

    public void setFromCache(boolean fromCache) {
        this.fromCache = fromCache;
    }

    public boolean isComputation() {
        return computation;
    }

    public void setComputation(boolean computation) {
        this.computation = computation;
    }
}
