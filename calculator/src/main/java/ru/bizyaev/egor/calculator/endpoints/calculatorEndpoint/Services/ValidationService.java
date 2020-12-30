package ru.bizyaev.egor.calculator.endpoints.calculatorEndpoint.Services;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationService {

    private boolean isLetter(String expression) {
        Pattern pattern = Pattern.compile("[A-Za-z]");
        Matcher matcher = pattern.matcher(expression);
        return !matcher.find();
    }

    public boolean isValid(String expression) {
        return isLetter(expression);
    }
}
