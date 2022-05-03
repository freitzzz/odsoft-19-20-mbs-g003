package com.twu.calculator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CalculatorTest {

	ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

	@Before
    public void setUp() {
        System.setOut(new PrintStream(outputContent));
    }
	
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
    public void shouldReturnOneOnFactorialOfZero() {
    	Calculator calculator = new Calculator();
    	double actualResult = calculator.doOperation("factorial", 0);
    	
    	assertThat(actualResult, is(1.0));
    }
    
    @Test
    public void shouldReturnOneHundredTwentyOnFactorialOfFive() {
    	Calculator calculator = new Calculator();
    	
    	// Set the accumulator initially to five
    	calculator.doOperation("add", 5);
    	
    	double actualResult = calculator.doOperation("factorial", 0);
    	
    	assertThat(actualResult, is(120.0));
    }
    
    @Test
    public void shouldReturnCorrectResultForFactorialOfFifty() {
    	Calculator calculator = new Calculator();
    	
    	// Set the accumulator initially to fifty
    	calculator.doOperation("add", 50);
    	
    	double actualResult = calculator.doOperation("factorial", 0);
    	
    	assertThat(actualResult, is(3.0414093201713376E64));
    }
    
    @Test
    public void shouldReturnCorrectResultForFactorialOfSeven() {
    	Calculator calculator = new Calculator();
    	
    	// Set the accumulator initially to seven
    	calculator.doOperation("add", 7);
    	
    	double actualResult = calculator.doOperation("factorial", 0);
    	
    	assertThat(actualResult, is(5040.0));
    }
    
    @Test
    public void shouldPrintErrorOnNegativeNumberFactorial() {
    	Calculator calculator = new Calculator();
    	
    	calculator.doOperation("subtract", 1);
    	
    	double actualResult = calculator.doOperation("factorial", 0);
    	String stringResult = outputContent.toString();
    	
    	System.out.println(stringResult);
    	
    	assertThat(actualResult, is(-1.0));
    	assertThat(stringResult, is("Factorial operation is only valid for non-negative integers" + System.getProperty("line.separator")));
    }
    
    @Test
    public void shouldPrintErrorOnNonIntegerFactorial() {
    	Calculator calculator = new Calculator();
    	
    	calculator.doOperation("add", 0.5);
    	
    	double actualResult = calculator.doOperation("factorial", 0);
    	String stringResult = outputContent.toString();
    	
    	assertThat(actualResult, is(0.5));
    	assertThat(stringResult, is("Factorial operation is only valid for non-negative integers" + System.getProperty("line.separator")));
    }

}
