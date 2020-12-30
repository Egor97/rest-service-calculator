package ru.bizyaev.egor.calculator.endpoints.calculatorEndpoint.Entities;

public class ExpressionEntity {

    private final String expression;
    private final String precision;

    public ExpressionEntity(String expression, String precision) {
        this.expression = expression;
        this.precision = precision;
    }

    public String getExpression() {
        return expression;
    }

    public Integer getPrecision() {
        return Integer.parseInt(precision);
    }
}
