package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate());
		assertEquals(Double.valueOf(0.20), DKK.getRate());
		assertEquals(Double.valueOf(1.5), EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.18);
		DKK.setRate(0.25);
		EUR.setRate(1.45);
		assertEquals(Double.valueOf(0.18), SEK.getRate());
		assertEquals(Double.valueOf(0.25), DKK.getRate());
		assertEquals(Double.valueOf(1.45), EUR.getRate());
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(2), SEK.universalValue(15));
		assertEquals(Integer.valueOf(4), DKK.universalValue(20));
		assertEquals(Integer.valueOf(225), EUR.universalValue(150));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(Integer.valueOf(27), SEK.valueInThisCurrency(20, DKK));
		assertEquals(Integer.valueOf(1125), DKK.valueInThisCurrency(150, EUR));
		assertEquals(Integer.valueOf(1), EUR.valueInThisCurrency(15, SEK));
	}

}
