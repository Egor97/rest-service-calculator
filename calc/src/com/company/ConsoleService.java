package com.company;

import java.util.Scanner;

import static java.lang.System.out;

public class ConsoleService {

    private String arithmeticExpressionFromConsole = consoleReceivingArithmeticExpression();

    String consoleReceivingArithmeticExpression() {
        out.print("Arithmetic Expression: ");
        Scanner scanner = new Scanner(System.in);

        arithmeticExpressionFromConsole = scanner.nextLine();

        scanner.close();

        return arithmeticExpressionFromConsole;
    }

    public String getArithmeticExpressionFromConsole() {
        return arithmeticExpressionFromConsole;
    }

}
