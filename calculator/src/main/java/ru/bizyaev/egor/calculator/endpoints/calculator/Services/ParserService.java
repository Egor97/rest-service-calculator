package ru.bizyaev.egor.calculator.endpoints.calculator.Services;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Stack;
import java.util.StringTokenizer;

@Component
public class ParserService {

    private final String OPERATORS = "+-*/";
    private final String SEPARATOR = ",";
    private final Stack<String> stackOperations = new Stack<>();
    private final Stack<String> stackRPN = new Stack<>();


    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isSeparator(String token) {
        return token.equals(SEPARATOR);
    }

    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private byte getPrecedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }

    public Stack<String> parse(String expression) throws ParseException {
        stackOperations.clear();
        stackRPN.clear();

        expression = expression.replace(" ", "").replace("(-", "(0-");

        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(expression,
                OPERATORS + SEPARATOR + "()", true);

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isSeparator(token)) {
                while (!stackOperations.empty()
                        && !isOpenBracket(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
            } else if (isOpenBracket(token)) {
                stackOperations.push(token);
            } else if (isCloseBracket(token)) {
                while (!stackOperations.empty()
                        && !isOpenBracket(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.pop();
            } else if (isNumber(token)) {
                stackRPN.push(token);
            } else if (isOperator(token)) {
                while (!stackOperations.empty()
                        && isOperator(stackOperations.lastElement())
                        && getPrecedence(token) <= getPrecedence(stackOperations
                        .lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.push(token);
            }
        }

        while (!stackOperations.empty()) {
            stackRPN.push(stackOperations.pop());
        }

        return stackRPN;
    }
}
