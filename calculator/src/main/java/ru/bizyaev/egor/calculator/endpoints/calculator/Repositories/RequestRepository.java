package ru.bizyaev.egor.calculator.endpoints.calculator.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.RequestDb;

public interface RequestRepository extends JpaRepository<RequestDb, Long> {
}
