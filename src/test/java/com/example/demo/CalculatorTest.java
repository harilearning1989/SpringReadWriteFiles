package com.example.demo;

import com.example.demo.jacoco.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        int result = calculator.add(5, 4);
        Assert.assertEquals(9, result);
    }

    @Test
    public void testSubtract() {
        int result = calculator.subtract(5, 4);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testMultiply() {
        int result = calculator.multiply(5, 4);
        Assert.assertEquals(20, result);
    }

    @Test
    public void testDivide() {
        int result = calculator.divide(5, 4);
        Assert.assertEquals(1, result);
    }

    @After
    public void clean() {
        calculator = null;
    }

}
