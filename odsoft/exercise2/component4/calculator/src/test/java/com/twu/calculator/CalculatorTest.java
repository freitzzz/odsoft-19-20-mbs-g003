package com.twu.calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class CalculatorTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    private double actualResult;

    @Test
    public void shouldReturnZeroWhenZeroIsAddedAsInitialCommand() {
        Calculator calculator = new Calculator();

        double actualResult = calculator.doOperation("add", 0);

        assertThat(actualResult, is(0.0));
    }

    @Test
    public void shouldReturnCorrectValueWhenAValueIsAddedAsInitialCommand() {
        Calculator calculator = new Calculator();

        double actualResult = calculator.doOperation("add", 10);

        assertThat(actualResult, is(10.0));
    }

    @Test
    public void shouldReturnCorrectValueWhenTwoAddCommandAreGiven() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 10);

        double actualResult = calculator.doOperation("add", 20);

        assertThat(actualResult, is(30.0));
    }

    @Test
    public void shouldReturnCorrectValueWhenFirstCommandIsAddAndSecondCommandIsSubtract() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 30);

        double actualResult = calculator.doOperation("subtract", 20);

        assertThat(actualResult, is(10.0));
    }

    @Test
    public void shouldReturnCorrectValueWhenFirstCommandIsAddAndSecondCommandIsMultiply() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 10);

        actualResult = calculator.doOperation("multiply", 20);

        assertThat(actualResult, is(200.0));
    }

    @Test
    public void shouldReturnCorrectValueWhenFirstCommandIsAddAndSecondCommandIsDivide() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 10);

        double actualResult = calculator.doOperation("divide", 2);

        assertThat(actualResult, is(5.0));
    }

    @Test
    public void shouldSetTheCalculatorStateToZeroWhenCancelCommandIsGiven() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 10);

        double actualResult = calculator.doOperation("cancel", 0);

        assertThat(actualResult, is(0.0));
    }

    @Test
    public void shouldExitOutOfTheCalculatorAppWhenExitCommandIsGiven() {
        Calculator calculator = new Calculator();
        exit.expectSystemExitWithStatus(0);
        calculator.doOperation("exit", 0);
    }

    @Test
    public void shouldReturnAbsoluteValueOfResult() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", -10);

        double actualResult = calculator.doOperation("abs", 0);

        assertThat(actualResult, is(10.0));
    }

    @Test
    public void shouldReturnNegativeValueOfResult() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 10);

        double actualResult = calculator.doOperation("neg", 0);

        assertThat(actualResult, is(-10.0));
    }

    @Test
    public void shouldReturnSquareRootOfResult() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 16);

        double actualResult = calculator.doOperation("sqrt", 0);

        assertThat(actualResult, is(4.0));
    }

    @Test

    public void shouldReturnSquareOfResult() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 4);

        double actualResult = calculator.doOperation("sqr", 0);

        assertThat(actualResult, is(16.0));
    }

    @Test
    public void shouldReturnCubeOfResult() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 4);

        double actualResult = calculator.doOperation("cube", 0);

        assertThat(actualResult, is(64.0));
    }

    @Test
    public void shouldReturnCubeRootOfResult() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 64);

        double actualResult = calculator.doOperation("cubert", 0);

        assertThat(actualResult, is(4.0));
    }
    
    @Test
    public void exponentialReturnsZeroIfResultIsZeroAndExponentialIsDifferentThanZero() {
    	Calculator calculator = new Calculator();
    	
    	double result = calculator.doOperation("exponential", 5);
    	
    	BigDecimal expectedResultAsBigDecimal = BigDecimal.valueOf(0f);
    	
    	BigDecimal resultAsBigDecimal = BigDecimal.valueOf(result);
    	
    	assertThat(resultAsBigDecimal, is(expectedResultAsBigDecimal));
    }

    @Test
    public void exponentialReturnsOneIfResultIsZeroAndExponentialIsZero() {
    	Calculator calculator = new Calculator();
    	
    	double result = calculator.doOperation("exponential", 0);
    	
    	BigDecimal expectedResultAsBigDecimal = BigDecimal.valueOf(1f);
    	
    	BigDecimal resultAsBigDecimal = BigDecimal.valueOf(result);
    	
    	assertThat(resultAsBigDecimal, is(expectedResultAsBigDecimal));
    }

    @Test
    public void exponentialReturnsOneIfExponentialIsZero() {
    	Calculator calculator = new Calculator();
    	
        calculator.doOperation("add", 5);

    	double result = calculator.doOperation("exponential", 0);
    	
    	BigDecimal expectedResultAsBigDecimal = BigDecimal.valueOf(1f);
    	
    	BigDecimal resultAsBigDecimal = BigDecimal.valueOf(result);
    	
    	assertThat(resultAsBigDecimal, is(expectedResultAsBigDecimal));
    }
    
    @Test
    public void shouldReturnExponentialOfResult() {
    	Calculator calculator = new Calculator();
    	
    	double exponent = 5;
    	
    	double addResult = calculator.doOperation("add", 5);
    	
    	double exponentialResult = calculator.doOperation("exponential", exponent);
    	
    	double expectedExponentialResult = Math.pow(addResult, exponent);
    	
    	BigDecimal exponentialResultAsBigDecimal = BigDecimal.valueOf(exponentialResult);
    	
    	BigDecimal expectedExponentialResultAsBigDecimal = BigDecimal.valueOf(expectedExponentialResult);
    	
    	assertThat(exponentialResultAsBigDecimal, is(expectedExponentialResultAsBigDecimal));
    }

}
