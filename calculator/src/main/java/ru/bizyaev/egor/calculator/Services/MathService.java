package ru.bizyaev.egor.calculator.Services;

import org.springframework.stereotype.Service;
import ru.bizyaev.egor.calculator.Entities.ExpressionEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


@Service
public class MathService {

    private final Stack<BigDecimal> stackOperands = new Stack<>();
    private final ArrayList<BigDecimal> list = new ArrayList<>();

    // boolean block
    private boolean isNumber(String element) {
        try {
            Double.parseDouble(element);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isOperator(String element) {
        String OPERATORS = "+-*/";
        return OPERATORS.contains(element);
    }

    // function block
    private boolean countingTheNumberOfElements(Stack<BigDecimal> stack) {
        return stack.size() >= 2;
    }

    private void getListElements(Stack<BigDecimal> stack, int precision) {
        int counter = 2;
        if (countingTheNumberOfElements(stack)) {
            while (counter > 0) {
                list.add(stack.pop().setScale(precision, RoundingMode.CEILING));
                counter--;
            }
        }
        Collections.reverse(list);
    }

    private void removeList(ArrayList<BigDecimal> list) {
        list.clear();
    }

    private void plus(ArrayList<BigDecimal> list, Stack<BigDecimal> stack, int precision) {
        stack.add(list.get(0).add(list.get(1)).setScale(precision, RoundingMode.CEILING));
        removeList(list);
    }

    private void minus(ArrayList<BigDecimal> list, Stack<BigDecimal> stack, int precision) {
        stack.add(list.get(0).subtract(list.get(1)).setScale(precision, RoundingMode.CEILING));
        removeList(list);
    }

    private void division(ArrayList<BigDecimal> list, Stack<BigDecimal> stack, int precision) {
        stack.add(list.get(0).divide(list.get(1), precision, RoundingMode.CEILING));
        removeList(list);
    }

    private void multiply(ArrayList<BigDecimal> list, Stack<BigDecimal> stack, int precision) {
        stack.add(list.get(0).multiply(list.get(1)).setScale(precision, RoundingMode.CEILING));
        removeList(list);
    }

    public void getNewStackOperands(String operator, ArrayList<BigDecimal> list, Stack<BigDecimal> stack,
                                    int precision) {
        switch (operator) {
            case "+": plus(list, stack, precision);
                break;
            case "-": minus(list, stack, precision);
                break;
            case "/": division(list, stack, precision);
                break;
            case "*": multiply(list, stack, precision);
                break;
        }
    }

    public BigDecimal getAnswer(Stack<String> stack, ExpressionEntity expressionEntity) {
        int precision = expressionEntity.getPrecision();
        while (!stack.empty()) {
            if (isOperator(stack.firstElement())) {
                String operator = stack.firstElement();
                stack.remove(0);
                getListElements(stackOperands, precision);
                getNewStackOperands(operator, list, stackOperands, precision);
            } else if (isNumber(stack.firstElement())) {
                stackOperands.add(BigDecimal.valueOf(Double.parseDouble(stack.remove(0))));
            }
        }

        return stackOperands.pop();
    }
}

