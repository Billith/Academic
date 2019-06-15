package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
    Currency SEK;
    Bank SweBank;
    Account testAccount;
    Account testAccount2;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        testAccount = new Account("Hans", SEK);
        testAccount2 = new Account("Lory", SEK);
        testAccount.deposit(new Money(10000000, SEK));
    }

    @Test
    public void testAddRemoveTimedPayment() throws AccountExistsException {
        // brakowało atrybutu name w klasie Account
        // przez co nie można było uzyskać nazwy konta na które wysyłana jest płatność
        // usunięto negacje ze sprawdzenia czy konto istnieje w metodzie deposit w klasie Bank
        SweBank.openAccount("test");
        testAccount.addTimedPayment("0", 1, 1, new Money(1000, SEK), SweBank, "test");
        assertTrue(testAccount.timedPaymentExists("0"));
        testAccount.removeTimedPayment("0");
        assertFalse(testAccount.timedPaymentExists("0"));
    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException, AccountExistsException {
        // nie można przetestować prywatnej klasy wewnętrznej w innej klasie
        //fail("nie można przetestować prywatnej klasy wewnętrznej w innej klasie");
        // usunięto jedno wywowałnie tick z funckji tick w klasie Account
        SweBank.openAccount("Bob");
        testAccount.addTimedPayment("1", 10, 1, new Money(1000, SEK), SweBank, "Bob");
        int balance = SweBank.getBalance("Bob").intValue();
        testAccount.tick();
        assertEquals(balance + 1000, SweBank.getBalance("Bob").intValue());
    }

    @Test
    public void testWithdraw() {
        testAccount.withdraw(new Money(1000000, SEK));
        assertEquals(Integer.valueOf(9000000), testAccount.getBalance().getAmount());
        testAccount.deposit(new Money(1000000, SEK));
    }

    @Test
    public void testGetBalance() {
        assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
    }
}
