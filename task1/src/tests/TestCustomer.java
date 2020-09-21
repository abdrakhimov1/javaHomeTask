package tests;

import main.Customer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestCustomer {
    private final String testName = "Name";
    private final String testLastName = "LastName";
    private final long testAccountId = 1234567890;

    Customer customer = new Customer(testName, testLastName);

    @Test
    public void checkOpenAccount() {

        assertTrue(customer.openAccount(testAccountId));
    }

    @Test
    public void checkCloseAccount() {
        assertFalse(customer.closeAccount());
    }

    @Test
    public void checkFullName() {
        assertEquals(testName + ' ' + testLastName, customer.fullName());
    }

    @Test
    public void checkWithdrawFromCurrentAccount() {
        assertFalse(customer.withdrawFromCurrentAccount(10));
    }

    @Test
    public void checkAddFromCurrentAccount() {
        customer.openAccount(testAccountId);
        assertTrue(customer.addMoneyToCurrentAccount(10));

    }


}
