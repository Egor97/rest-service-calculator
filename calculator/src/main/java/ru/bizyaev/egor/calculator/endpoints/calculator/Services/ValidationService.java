package ru.bizyaev.egor.calculator.endpoints.calculator.Services;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationService {

    private boolean checkEmptyField(String expression) {
        return !expression.trim().equals("");
    }

    private boolean isLegalSign(String expression) {
        Pattern pattern;
        pattern = Pattern.compile("[^.()\\s\\d*/+-]");
        Matcher matcher = pattern.matcher(expression);
        return !matcher.find();
    }

    private boolean checkBrackets(String expression) {
        int check = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (check < 0) {
                return false;
            }

            if (expression.charAt(i) == '(' && expression.charAt(i + 1) != ')') {
                check++;
                continue;
            }
            if (expression.charAt(i) == ')')
                check--;
        }
        return check == 0;
    }

    private boolean checkingRepetitionSign(String expression) {
        Pattern pattern = Pattern.compile("([\\s]*[-+/*][\\s]*[+/*])|(\\s*[-+/*]\\s*[-]\\s)");
        Matcher matcher = pattern.matcher(expression);

        return !matcher.find();
    }

    public boolean isValid(String expression) {
        return checkEmptyField(expression) && isLegalSign(expression) && checkBrackets(expression) &&
                checkingRepetitionSign(expression);
    }
}
