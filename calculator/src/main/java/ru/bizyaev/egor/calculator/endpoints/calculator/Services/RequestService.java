package ru.bizyaev.egor.calculator.endpoints.calculator.Services;

import org.springframework.stereotype.Service;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.RequestDb;

import java.util.List;

@Service
public interface RequestService {
    RequestDb getRequest();
    void createRequest(RequestDb requestDb);
    List<RequestDb> getAllRequestsByLogin(Long id);
}
