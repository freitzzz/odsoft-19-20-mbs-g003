package com.twu.calculator;

public class Calculator {

    private double accumulator;
    
    public static double computeFactorial(double number) {
    	if (number <= 1.0) {
    		return 1.0;
		}
    	return number * computeFactorial(number - 1.0);
	}

    public double doOperation(String operation, double operand) {
        switch (operation) {
            case "add":
                accumulator += operand;
                break;
            case "subtract":
                accumulator -= operand;
                break;
            case "multiply":
                accumulator *= operand;
                break;
            case "divide":
                accumulator /= operand;
                break;
            case "abs":
                accumulator = Math.abs(accumulator);
                break;
            case "neg":
                accumulator = -accumulator;
                break;
            case "sqrt":
                accumulator = Math.sqrt(accumulator);
                break;
            case "sqr":
                accumulator = Math.pow(accumulator, 2);
                break;
            case "cube":
                accumulator = Math.pow(accumulator, 3);
                break;
            case "cubert":
                accumulator = Math.cbrt(accumulator);
                break;
            case "factorial":
            	// User trying to do a factorial of negative or non integer number
            	if (accumulator < 0 || accumulator != Math.floor(accumulator)) {
            		System.out.println("Factorial operation is only valid for non-negative integers");
            		break;
            	}
            	
            	accumulator = computeFactorial(accumulator);
            	break;
            case "cancel":
                accumulator = 0;
                break;
            case "exit":
                System.exit(0);
        }
        return accumulator;
    }
}
