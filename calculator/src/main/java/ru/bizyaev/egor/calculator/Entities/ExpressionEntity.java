package ru.bizyaev.egor.calculator.Entities;

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

    public int getPrecision() {
        return Integer.parseInt(precision);
    }

    @Override
    public String toString() {
        return "expression= " + expression + " precision= " + precision + " ";
    }
}
