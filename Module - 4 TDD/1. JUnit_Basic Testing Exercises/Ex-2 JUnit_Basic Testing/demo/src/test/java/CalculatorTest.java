package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testAddition() {
        assertEquals(10, calculator.add(6, 4));
    }

    @Test
    public void testSubtraction() {
        assertEquals(5, calculator.subtract(9, 4));
    }

    @Test
    public void testMultiplication() {
        assertEquals(24, calculator.multiply(6, 4));
    }

    @Test
    public void testDivision() {
        assertEquals(5, calculator.divide(20, 4));
    }
}