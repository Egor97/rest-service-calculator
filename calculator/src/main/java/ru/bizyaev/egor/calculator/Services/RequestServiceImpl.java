package ru.bizyaev.egor.calculator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bizyaev.egor.calculator.Repositories.RequestRepository;
import ru.bizyaev.egor.calculator.Entities.RequestDb;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void createRequest(RequestDb requestDb) {
        requestRepository.save(requestDb);
    }

    @Override
    public List<RequestDb> getAllRequestsByLogin(String login) {
        List<RequestDb> requestDbList = new ArrayList<>();
        requestRepository.findAll().stream()
                .filter(requestDb -> login.equals(requestDb.getLogin()))
                .forEach(requestDbList::add);
        return requestDbList;
    }
}
