package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
		SweBank.openAccount("Bob");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(DKK, DanskeBank.getCurrency());
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		// Należało zmienić motdę get na put w metodzie openAccount
		try {
			SweBank.getBalance("Bob");
		} catch (AccountDoesNotExistException e) {
			fail();
		}
		try {
			SweBank.openAccount("Bob");
			fail();
		} catch (AccountExistsException ee) { }
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		// należało dodać negację przy sprawdzeniu czy konto istnieje w metodzie deposit with klasie Bank
		int balance = SweBank.getBalance("Bob");
		SweBank.deposit("Bob", new Money(1000, SEK));
		assertTrue(SweBank.getBalance("Bob").equals(balance + 1000));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		// należało zmienić deposit na withdraw w metodzie withdraw w klasie bank
		int balance = SweBank.getBalance("Bob");
		SweBank.withdraw("Bob", new Money(1000, SEK));
		assertTrue(SweBank.getBalance("Bob").equals(balance - 1000));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		assertEquals(1000, SweBank.getBalance("Bob").intValue());
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		int balance1 = Nordea.getBalance("Bob");
		int balance2 = SweBank.getBalance("Bob");
		SweBank.transfer("Bob", Nordea, "Bob", new Money(1000, SEK));
		assertEquals(balance1 + 1000, Nordea.getBalance("Bob").intValue());
		assertEquals(balance2 - 1000, SweBank.getBalance("Bob").intValue());
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// dodano " - 1" w konstrukorze w klasie TimedPayment
		int balance1 = SweBank.getBalance("Ulrika");
		int balance2 = SweBank.getBalance("Bob");
		SweBank.addTimedPayment("Bob", "0", 12, 3, new Money(1000, SEK), SweBank, "Ulrika");
		SweBank.tick();
		SweBank.tick();
		SweBank.tick();
		assertEquals(balance1 + 1000, SweBank.getBalance("Ulrika").intValue());
		assertEquals(balance2 - 1000, SweBank.getBalance("Bob").intValue());
	}
}
