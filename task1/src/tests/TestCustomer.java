package tests;

import main.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class TestCustomer {
    private final String testName = "Name";
    private final String testLastName = "LastName";
    private final long testAccountId = 1234567890;

    Customer customer = new Customer(testName, testLastName);

    @Test
    public void checkOpenAccount() {
        assert customer.openAccount(testAccountId);
    }

    @Test
    public void checkCloseAccount() {
        assert !customer.closeAccount();
    }

    @Test
    public void checkFullName() {
        assertEquals(testName + ' ' + testLastName, customer.fullName());
    }

    @Test
    public void checkWithdrawFromCurrentAccount() {
        assert !customer.withdrawFromCurrentAccount(10);
    }

    @Test
    public void checkAddFromCurrentAccount() {
        assert customer.addMoneyToCurrentAccount(10);
    }


    public static void main(String[] args ){

        TestCustomer testCustomer = new TestCustomer();

        testCustomer.checkCloseAccount();
        testCustomer.checkFullName();
        testCustomer.checkOpenAccount();
        testCustomer.checkWithdrawFromCurrentAccount();
        testCustomer.checkAddFromCurrentAccount();

    }
}