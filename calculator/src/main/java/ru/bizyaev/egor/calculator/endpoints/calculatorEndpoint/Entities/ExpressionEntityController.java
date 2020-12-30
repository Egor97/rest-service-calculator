package ru.bizyaev.egor.calculator.endpoints.calculatorEndpoint.Entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bizyaev.egor.calculator.endpoints.calculatorEndpoint.Exceptions.MethodArgumentNotValidException;
import ru.bizyaev.egor.calculator.endpoints.calculatorEndpoint.Services.ArithmeticFunctionService;
import ru.bizyaev.egor.calculator.endpoints.calculatorEndpoint.Services.ParserService;
import ru.bizyaev.egor.calculator.endpoints.calculatorEndpoint.Services.ValidationService;
import java.text.ParseException;
import java.util.Stack;

@RestController
@RequestMapping("/")
public class ExpressionEntityController {

    private final ParserService parserService;
    private final ArithmeticFunctionService arithmeticFunctionService;
    private final ValidationService validationService;

    @Autowired
    public ExpressionEntityController(ParserService parserService, ArithmeticFunctionService arithmeticFunctionService, ValidationService validationService) {
        this.parserService = parserService;
        this.arithmeticFunctionService = arithmeticFunctionService;
        this.validationService = validationService;
    }

    @PostMapping
       public void getExpression(@RequestBody ExpressionEntity expressionEntity) throws MethodArgumentNotValidException {
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
            arithmeticFunctionService.getAnswer(stack, precision);
        } else {
            throw new MethodArgumentNotValidException();
        }
    }
}
