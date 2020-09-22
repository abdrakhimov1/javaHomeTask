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


    @Test
    public void openAccount_returnsTrue_WhenNewAccountCreated() {
        Customer customer = new Customer(testName, testLastName);
        boolean isSuccess = customer.openAccount(testAccountId);
        assertTrue(isSuccess);
    }

    @Test
    public void openAccount_returnsFalse_WhenCustomerAlreadyHasOpenedAccount() {
        Customer customer = new Customer(testName, testLastName);
        customer.openAccount(testAccountId);
        boolean isNotSuccess = customer.openAccount(testAccountId);
        assertFalse(isNotSuccess);
    }

    @Test
    public void closeAccount_returnsFalse_WhenCustomerHasNoActiveAccountToClose() {
        Customer customer = new Customer(testName, testLastName);
        boolean isNotSuccess = customer.closeAccount();
        assertFalse(isNotSuccess);
    }

    @Test
    public void closeAccount_returnsTrue_WhenCustomersAccountWasClosed() {
        Customer customer = new Customer(testName, testLastName);
        customer.openAccount(testAccountId);
        boolean isSuccess = customer.closeAccount();
        assertTrue(isSuccess);
    }

    @Test
    public void fullName_returnsCustomerFullName_asString() {
        Customer customer = new Customer(testName, testLastName);
        assertEquals(testName + ' ' + testLastName, customer.fullName());
    }

    @Test
    public void withdrawFromCurrentAccount_returnsFalseWhenCustomerHasNoOpenAccounts() {
        Customer customer = new Customer(testName, testLastName);
        boolean isNotSucess = customer.withdrawFromCurrentAccount(10);
        assertFalse(isNotSucess);
    }

    @Test
    public void withdrawFromCurrentAccount_returnsTrueWhenCustomerHasOpenAccountAndEnoughMoneyOnIt() {
        Customer customer = new Customer(testName, testLastName);
        customer.openAccount(testAccountId);
        customer.addMoneyToCurrentAccount(20);
        boolean isSuccess = customer.withdrawFromCurrentAccount(10);
        assertTrue(isSuccess);
    }

    @Test
    public void withdrawFromCurrentAccount_returnsFalseWhenCustomerHasOpenAccountButDontHaveEnoughMoneyOnIt() {
        Customer customer = new Customer(testName, testLastName);
        customer.addMoneyToCurrentAccount(5);
        boolean isNotSuccess = customer.withdrawFromCurrentAccount(10);
        assertFalse(isNotSuccess);
    }

    @Test
    public void addMoneyToCurrentAccount_returnsTrue_WhenCustomerHasOpenAccountAndWhenAmountIsGreaterThanZero() {
        Customer customer = new Customer(testName, testLastName);
        customer.openAccount(testAccountId);
        boolean isSuccess = customer.addMoneyToCurrentAccount(10);
        assertTrue(isSuccess);
    }
}
