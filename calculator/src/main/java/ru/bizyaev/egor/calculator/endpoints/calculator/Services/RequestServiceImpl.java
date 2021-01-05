package ru.bizyaev.egor.calculator.endpoints.calculator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.RequestDb;
import ru.bizyaev.egor.calculator.endpoints.calculator.Repositories.RequestRepository;

import java.util.Collections;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public RequestDb getRequest() {
        return null;
    }

    @Override
    public void createRequest(RequestDb requestDb) {
        requestRepository.save(requestDb);
    }

    @Override
    public List<RequestDb> getAllRequestsByLogin(Long id) {
        return requestRepository.findAllById(Collections.singleton(id));
    }
}
