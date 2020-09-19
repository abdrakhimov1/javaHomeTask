package tests;
import main.Account;
import main.Customer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestAccount {
    private final long testId = 123456789;

    Account testAccount = new Account(testId);

    @Test
    public void checkAdd() {
        assert testAccount.add(5000);
    }

    @Test
    public void checkWithdraw() {
        assert !testAccount.withdraw(3000);
    }

    public static void main(String[] args ){

        TestAccount testAccount1 = new TestAccount();

        testAccount1.checkAdd();
        testAccount1.checkWithdraw();

    }

}
