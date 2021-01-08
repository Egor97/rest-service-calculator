package ru.bizyaev.egor.calculator.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bizyaev.egor.calculator.Entities.RequestDb;

public interface RequestRepository extends JpaRepository<RequestDb, Long> {
}
