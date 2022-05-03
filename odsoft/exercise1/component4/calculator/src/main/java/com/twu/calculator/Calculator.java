package com.twu.calculator;

public class Calculator {

    private double accumulator;

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
            case "exponential":
            	accumulator = exponential(accumulator, operand);
            	break;
            case "cancel":
                accumulator = 0;
                break;
            case "exit":
                System.exit(0);
        }
        return accumulator;
    }
    
    /**
     * Calculates the exponential of a base number using an exponent
     * @param base double This parameter indicates the base number which the exponent will be applied
     * @param exponent This parameter indicates the exponent to be used on the exponential function
     * @return The output of the exponential function as a double
     */
    private static double exponential(double base, double exponent) {
    	
    	return Math.pow(base, exponent);
    	
    }
    
}
