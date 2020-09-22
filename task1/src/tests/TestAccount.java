package tests;
import main.Account;
import main.Customer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAccount {
    private final long testId = 123456789;

    @Test
    public void addsMoney_returnsTrue_whenAmountIsGreaterThanZero() {
        Account testAccount = new Account(testId);
        boolean isSuccess = testAccount.add(5000);
        assertTrue(isSuccess);
    }

    @Test
    public void withdrawMoney_returnsFalse_whenAmountIsLessThenNecessary() {
        Account testAccount = new Account(testId);
        boolean isNotSuccess =  testAccount.withdraw(3000);
        assertFalse(isNotSuccess);
    }
}
