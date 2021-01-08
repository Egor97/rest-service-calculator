package ru.bizyaev.egor.calculator.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "request")
@Data
public class RequestDb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login", nullable = false)
    @JsonIgnore
    private PersonEntity personEntity;

    @Column
    private boolean fromCache;

    @Column
    private boolean computation;

    @Column
    private BigDecimal result;

    public String getLogin() {
        return personEntity.getLogin();
    }
}
