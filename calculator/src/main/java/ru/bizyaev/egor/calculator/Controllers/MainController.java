package ru.bizyaev.egor.calculator.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.bizyaev.egor.calculator.Entities.ExpressionEntity;
import ru.bizyaev.egor.calculator.Exceptions.PersonNotFoundException;
import ru.bizyaev.egor.calculator.Services.*;
import ru.bizyaev.egor.calculator.Caches.Cache;
import ru.bizyaev.egor.calculator.Entities.RequestDb;
import ru.bizyaev.egor.calculator.Exceptions.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/")
public class MainController {

    private final ParserService parserService;
    private final MathService mathService;
    private final ValidationService validationService;
    private final PersonService personService;
    private final RequestService requestService;
    private final Cache cache;


    @Autowired
    public MainController(ParserService parserService, MathService mathService,
                          ValidationService validationService, PersonService personService,
                          RequestService requestService, Cache cache) {
        this.parserService = parserService;
        this.mathService = mathService;
        this.validationService = validationService;
        this.personService = personService;
        this.requestService = requestService;
        this.cache = cache;
    }

    private boolean checkAccessLogin(String login) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((User) principal).getUsername().equals(login);
    }

    @GetMapping("/{login}")
    public List<RequestDb> getDataPersonByLogin(@PathVariable String login) throws PersonNotFoundException {
        List<RequestDb> requests;

        if (checkAccessLogin(login)) {
            requests = requestService.getAllRequestsByLogin(personService.getPerson(login).getLogin());
        } else {
            throw new PersonNotFoundException("Access is denied");
        }

        return requests;
    }

    @PostMapping("/{login}")
    private void getExpression(@PathVariable String login, @RequestBody ExpressionEntity expressionEntity)
            throws MethodArgumentNotValidException {
        String expression = expressionEntity.getExpression();
        Stack<String> stack = null;
        int precision = expressionEntity.getPrecision();

        if (checkAccessLogin(login)) {

            if (validationService.isValid(expression)) {

                try {
                    stack = parserService.parse(expressionEntity.getExpression());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                assert stack != null;
                RequestDb request = new RequestDb();
                if (!cache.checkCache(expressionEntity.toString())) {
                    BigDecimal result = mathService.getAnswer(stack, precision);
                    cache.saveResult(expressionEntity.toString(), result);
                    request.setPersonEntity(personService.getPerson(login));
                    request.setComputation(true);
                    request.setResult(result);
                } else {
                    request.setPersonEntity(personService.getPerson(login));
                    request.setFromCache(true);
                    request.setResult(cache.getSaveResult());
                }
                requestService.createRequest(request);
            } else {
                throw new MethodArgumentNotValidException();
            }

        } else {
            throw new PersonNotFoundException("Access is denied");
        }
    }
}
