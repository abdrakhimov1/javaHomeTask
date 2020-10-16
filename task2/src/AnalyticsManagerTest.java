import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsManagerTest {
    TransactionManager transactionManager = new TransactionManager();
    Account account1 = new Account(123, transactionManager);
    Account account2 = new Account(124, transactionManager);
    Account account3 = new Account(125, transactionManager);
    AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);

    @Before
    void prepearing_TransactionManager_And_Accounts() {
        account3.addCash(10000);
        account1.addCash(10000);
        Transaction transaction1 = transactionManager.createTransaction(100, account3,account1);
        Transaction transaction2 = transactionManager.createTransaction(200, account3,account1);
        Transaction transaction3 = transactionManager.createTransaction(300, account3,account2);
        Transaction transaction4 = transactionManager.createTransaction(100, account3,account2);
        Transaction transaction5 = transactionManager.createTransaction(500, account3,account2);
    }

    @Test
    void making_Transactions_From_Different_Accounts_Getting_Most_Frequent_Beneficiary_Of_Account() {
        prepearing_TransactionManager_And_Accounts();
        assertEquals(account2, analyticsManager.mostFrequentBeneficiaryOfAccount(account3));
    }

    @Before
    void prepearing_Accounts_Before_Getting_Top_Ten_Expensive_Purchases_Of_Account_Test() {
        account1.addCash(10000);
        Transaction transaction1 = transactionManager.createTransaction(100, account1,account2);
        Transaction transaction2 = transactionManager.createTransaction(1200, account1,account2);
        Transaction transaction3 = transactionManager.createTransaction(300, account1,account2);
        Transaction transaction4 = transactionManager.createTransaction(1100, account1,account2);
        Transaction transaction5 = transactionManager.createTransaction(500, account1,account2);
        Transaction transaction6 = transactionManager.createTransaction(1600, account1,account2);
        Transaction transaction7 = transactionManager.createTransaction(1700, account1,account2);
        Transaction transaction8 = transactionManager.createTransaction(800, account1,account2);
        Transaction transaction9 = transactionManager.createTransaction(1900, account1,account2);
        Transaction transaction10 = transactionManager.createTransaction(1000, account1,account2);
        Transaction transaction11 = transactionManager.createTransaction(100, account1,account2);
        Transaction transaction12 = transactionManager.createTransaction(200, account1,account2);
        Transaction transaction13 = transactionManager.createTransaction(1100, account2,account1);
    }

    @Test
    void getting_Top_Ten_Expensive_Purchases_Of_Account() {
        prepearing_Accounts_Before_Getting_Top_Ten_Expensive_Purchases_Of_Account_Test();
        assertEquals(10, analyticsManager.topTenExpensivePurchases(account1).size());
    }
}