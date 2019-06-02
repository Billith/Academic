package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
    Currency SEK;
    Bank SweBank;
    Account testAccount;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        testAccount = new Account("Hans", SEK);
        testAccount.deposit(new Money(10000000, SEK));
        testAccount.addTimedPayment("0", 1, 2, new Money(100, SEK), SweBank, "1");
    }

    @Test
    public void testAddRemoveTimedPayment() {
        assertTrue(testAccount.timedPaymentExists("0"));
    }

    @Test
    public void testTimedPayment() {

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
