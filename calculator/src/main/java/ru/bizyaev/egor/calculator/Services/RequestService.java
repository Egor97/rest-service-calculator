package ru.bizyaev.egor.calculator.Services;

import org.springframework.stereotype.Service;
import ru.bizyaev.egor.calculator.Entities.RequestDb;

import java.util.List;

@Service
public interface RequestService {
    void createRequest(RequestDb requestDb);
    List<RequestDb> getAllRequestsByLogin(String login);
}
