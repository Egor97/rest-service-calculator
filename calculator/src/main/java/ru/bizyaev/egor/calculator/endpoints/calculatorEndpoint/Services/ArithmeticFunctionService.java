package ru.bizyaev.egor.calculator.endpoints.calculatorEndpoint.Services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


@Component
public class ArithmeticFunctionService {

    private final Stack<Double> stackOperands = new Stack<>();
    private final ArrayList<Double> list = new ArrayList<>();

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
    private boolean countingTheNumberOfElements(Stack<Double> stack) {
        return stack.size() >= 2;
    }

    private void getListElements(Stack<Double> stack) {
        int counter = 2;
        if (countingTheNumberOfElements(stack)) {
            while (counter > 0) {
                list.add(stack.pop());
                counter--;
            }
        }
        Collections.reverse(list);
    }

    private void removeList(ArrayList<Double> list) {
        list.clear();
    }

    private void plus(ArrayList<Double> list, Stack<Double> stack) {
        stack.add(list.get(0) + list.get(1));
        removeList(list);
    }

    private void minus(ArrayList<Double> list, Stack<Double> stack) {
        stack.add(list.get(0) - list.get(1));
        removeList(list);
    }

    private void division(ArrayList<Double> list, Stack<Double> stack) {
        stack.add(list.get(0) / list.get(1));
        removeList(list);
    }

    private void multiply(ArrayList<Double> list, Stack<Double> stack) {
        stack.add(list.get(0) * list.get(1));
        removeList(list);
    }

    public void getNewStackOperands(String operator, ArrayList<Double> list, Stack<Double> stack) {
        switch (operator) {
            case "+": plus(list, stack);
                break;
            case "-": minus(list, stack);
                break;
            case "/": division(list, stack);
                break;
            case "*": multiply(list, stack);
                break;
        }
    }

    public Double getAnswer(Stack<String> stack, int precision) {
        while (!stack.empty()) {
            if (isOperator(stack.firstElement())) {
                String operator = stack.firstElement();
                stack.remove(0);
                getListElements(stackOperands);
                getNewStackOperands(operator, list, stackOperands);
            } else if (isNumber(stack.firstElement())) {
                stackOperands.add(Double.parseDouble(stack.remove(0)));
            }
        }

        return stackOperands.pop();
    }
}

