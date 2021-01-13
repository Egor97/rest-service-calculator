package ru.bizyaev.egor.calculator.Services;

import org.springframework.stereotype.Service;
import ru.bizyaev.egor.calculator.Entities.ExpressionEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private boolean checkEmptyField(String expression) {
        Pattern pattern = Pattern.compile("(^[.]$)|(^[*/+-]$)|(^[\\d]*([*/+-])*$)|(^[*/+-]\\s*[\\d]*([*/+-])*$)");
        Matcher matcher = pattern.matcher(expression.trim());
        return !matcher.find();
    }

    private boolean isLegalSign(String expression) {
        Pattern pattern = Pattern.compile("[^.()\\s\\d*/+-]");
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

    private boolean checkPrecision(String precision) {
        Pattern pattern = Pattern.compile("^[\\d]+$");
        Matcher matcher = pattern.matcher(precision);
        return matcher.find();
    }

    public boolean isValid(ExpressionEntity expressionEntity) {
        String expression = expressionEntity.getExpression();
        String precision = String.valueOf(expressionEntity.getPrecision());

        return checkEmptyField(expression) && isLegalSign(expression) &&
                checkBrackets(expression) && checkingRepetitionSign(expression) &&
                checkPrecision(precision);
    }
}
