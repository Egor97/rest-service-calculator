package ru.bizyaev.egor.calculator.endpoints.calculator.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bizyaev.egor.calculator.endpoints.calculator.Entities.ExpressionEntity;
import ru.bizyaev.egor.calculator.endpoints.calculator.Exceptions.MethodArgumentNotValidException;
import ru.bizyaev.egor.calculator.endpoints.calculator.Services.ArithmeticFunctionService;
import ru.bizyaev.egor.calculator.endpoints.calculator.Services.ParserService;
import ru.bizyaev.egor.calculator.endpoints.calculator.Services.ValidationService;
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
       private void getExpression(@RequestBody ExpressionEntity expressionEntity) throws MethodArgumentNotValidException {
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
            System.out.println(arithmeticFunctionService.getAnswer(stack, precision));
        } else {
            throw new MethodArgumentNotValidException();
        }
    }
}
