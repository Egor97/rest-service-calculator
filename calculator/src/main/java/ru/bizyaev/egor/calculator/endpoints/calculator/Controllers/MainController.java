package ru.bizyaev.egor.calculator.endpoints.calculator.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.Cache;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.ExpressionEntity;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.RequestDb;
import ru.bizyaev.egor.calculator.endpoints.calculator.Exceptions.MethodArgumentNotValidException;
import ru.bizyaev.egor.calculator.endpoints.calculator.Services.*;

import java.text.ParseException;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/")
public class MainController {

    private final ParserService parserService;
    private final ArithmeticFunctionService arithmeticFunctionService;
    private final ValidationService validationService;
    private final PersonService personService;
    private final RequestService requestService;
    private final Cache cache;

    @Autowired
    public MainController(ParserService parserService, ArithmeticFunctionService arithmeticFunctionService,
                          ValidationService validationService, PersonService personService,
                          RequestService requestService, Cache cache) {
        this.parserService = parserService;
        this.arithmeticFunctionService = arithmeticFunctionService;
        this.validationService = validationService;
        this.personService = personService;
        this.requestService = requestService;
        this.cache = cache;
    }

    @GetMapping("/{login}")
    public List<RequestDb> getDataPersonByLogin(@PathVariable String login) {
        return requestService.getAllRequestsByLogin(personService.getPerson(login).getId());
    }

    @PostMapping("/{login}")
    private void getExpression(@PathVariable String login, @RequestBody ExpressionEntity expressionEntity)
            throws MethodArgumentNotValidException {
        String expression = expressionEntity.getExpression();
        Stack<String> stack = null;
        int precision = expressionEntity.getPrecision();

        if (validationService.isValid(expression)) {
            try {
                stack = parserService.parse(expressionEntity.getExpression());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            assert stack != null;
            RequestDb request = new RequestDb();
            if (!cache.getCache().containsKey(expressionEntity.toString())) {
                cache.saveItem(expressionEntity, arithmeticFunctionService.getAnswer(stack, precision));
                request.setPersonEntity(personService.getPerson(login));
                request.setComputation(true);
            } else {
                request.setPersonEntity(personService.getPerson(login));
                request.setFromCache(true);
            }
            requestService.createRequest(request);
        } else {
            throw new MethodArgumentNotValidException();
        }
    }
}
