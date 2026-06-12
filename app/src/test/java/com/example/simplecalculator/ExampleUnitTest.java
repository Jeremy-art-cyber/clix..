package com.example.simplecalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    // ---------------------------
    // Additional Calculator Tests
    // ---------------------------

    @Test
    public void subtraction_isCorrect() {
        assertEquals(2, 5 - 3);
    }

    @Test
    public void multiplication_isCorrect() {
        assertEquals(15, 3 * 5);
    }

    @Test
    public void division_isCorrect() {
        assertEquals(4, 20 / 5);
    }

    @Test
    public void modulus_isCorrect() {
        assertEquals(1, 10 % 3);
    }

    @Test
    public void power_isCorrect() {
        assertEquals(25, (int) Math.pow(5, 2));
    }

    @Test
    public void squareRoot_isCorrect() {
        assertEquals(4.0, Math.sqrt(16), 0.001);
    }

    @Test
    public void maxNumber_isCorrect() {
        assertEquals(10, Math.max(10, 5));
    }

    @Test
    public void minNumber_isCorrect() {
        assertEquals(5, Math.min(10, 5));
    }

    @Test
    public void absoluteValue_isCorrect() {
        assertEquals(8, Math.abs(-8));
    }

    @Test
    public void negativeAddition_isCorrect() {
        assertEquals(-5, -2 + (-3));
    }

    @Test
    public void decimalCalculation_isCorrect() {
        assertEquals(5.5, 2.5 + 3.0, 0.001);
    }
}
