import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calc = new Calculator();
		int a = 7866;
		int b = 3453;
		
		int result = calc.add(a, b);
		
		int expected = 11319;
		assertEquals(expected,result);
	}

}
