package tests;
import main.Account;
import main.Customer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAccount {
    private final long testId = 123456789;

    Account testAccount = new Account(testId);

    @Test
    public void checkAdd() {
        assertTrue(testAccount.add(5000));
    }

    @Test
    public void checkWithdraw() {
        assertFalse(testAccount.withdraw(3000));
    }
}
