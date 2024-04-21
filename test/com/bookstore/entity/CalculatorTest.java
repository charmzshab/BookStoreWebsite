package com.bookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		int a = 1;
		int b = 2;
		int result = calculator.add(a, b);
		int expected = 3;
		assertEquals(expected, result);
		

	}

}
