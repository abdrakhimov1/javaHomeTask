import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsManagerTest {

    @Test
    void mostFrequentBeneficiaryOfAccount() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(123, transactionManager);
        Account account2 = new Account(124, transactionManager);
        Account account3 = new Account(125, transactionManager);
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);
        account3.addCash(10000);
        account1.addCash(10000);
        Transaction transaction1 = transactionManager.createTransaction(100, account3,account1);
        Transaction transaction2 = transactionManager.createTransaction(200, account3,account1);
        Transaction transaction3 = transactionManager.createTransaction(300, account3,account2);
        Transaction transaction4 = transactionManager.createTransaction(100, account3,account2);
        Transaction transaction5 = transactionManager.createTransaction(500, account3,account2);
        assertEquals(124, analyticsManager.mostFrequentBeneficiaryOfAccount(account3));
    }

    @Test
    void topTenExpensivePurchasesWithSystemOutDecreisCheck() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(123, transactionManager);
        Account account2 = new Account(124, transactionManager);
        AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);
        account1.addCash(10000);
        Transaction transaction1 = transactionManager.createTransaction(100, account1,account2);
        Transaction transaction2 = transactionManager.createTransaction(200, account1,account2);
        Transaction transaction3 = transactionManager.createTransaction(300, account1,account2);
        Transaction transaction4 = transactionManager.createTransaction(100, account1,account2);
        Transaction transaction5 = transactionManager.createTransaction(500, account1,account2);
        Transaction transaction6 = transactionManager.createTransaction(600, account1,account2);
        Transaction transaction7 = transactionManager.createTransaction(700, account1,account2);
        Transaction transaction8 = transactionManager.createTransaction(800, account1,account2);
        Transaction transaction9 = transactionManager.createTransaction(900, account1,account2);
        Transaction transaction10 = transactionManager.createTransaction(1000, account1,account2);
        Transaction transaction11 = transactionManager.createTransaction(100, account1,account2);
        Transaction transaction12 = transactionManager.createTransaction(200, account1,account2);
        Transaction transaction13 = transactionManager.createTransaction(1100, account2,account1);
        for (Transaction transaction: analyticsManager.topTenExpensivePurchases(account1)
             ) {
            System.out.println(transaction.getAmount());
        }
        assertEquals(10, analyticsManager.topTenExpensivePurchases(account1).size());
    }
}