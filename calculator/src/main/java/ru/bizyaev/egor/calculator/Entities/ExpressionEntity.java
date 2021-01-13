package ru.bizyaev.egor.calculator.Entities;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpressionEntity that = (ExpressionEntity) o;
        return Objects.equals(expression, that.expression) && Objects.equals(precision, that.precision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression, precision);
    }

    @Override
    public String toString() {
        return "expression= " + expression + " precision= " + precision + " ";
    }
}
