package com.company;

import java.text.ParseException;
import java.util.Stack;

public class ExpressionEntity {
    private final Stack<String> stack;

    ConsoleService cs = new ConsoleService();
    ParserService ps = new ParserService();
    FunctionService fs = new FunctionService();

    String expression = cs.getArithmeticExpressionFromConsole();

    public ExpressionEntity() {
        this.stack = result();
    }

    public Stack<String> result() {
        try {
            return ps.parse(expression);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double getResult() {
        return fs.getAnswer(stack);

    }
}
