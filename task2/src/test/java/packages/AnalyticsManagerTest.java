package test.java.packages;

import main.java.packages.DebitCard;
import main.java.packages.AnalyticsManager;
import main.java.packages.Transaction;
import main.java.packages.TransactionManager;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnalyticsManagerTest {
    TransactionManager transactionManager = new TransactionManager();
    DebitCard debitCard1 = new DebitCard(123, transactionManager, 3);
    DebitCard debitCard2 = new DebitCard(124, transactionManager, 2);
    DebitCard debitCard3 = new DebitCard(125, transactionManager, 5);
    AnalyticsManager analyticsManager = new AnalyticsManager(transactionManager);

    @Before
    void prepearing_TransactionManager_And_Accounts() {
        debitCard3.addCash(10000);
        debitCard1.addCash(10000);
        Transaction transaction1 = transactionManager.createTransaction(100, debitCard3, debitCard1);
        Transaction transaction2 = transactionManager.createTransaction(200, debitCard3, debitCard1);
        Transaction transaction3 = transactionManager.createTransaction(300, debitCard3, debitCard2);
        Transaction transaction4 = transactionManager.createTransaction(100, debitCard3, debitCard2);
        Transaction transaction5 = transactionManager.createTransaction(500, debitCard3, debitCard2);
//        assertEquals(124, analyticsManager.mostFrequentBeneficiaryOfAccount(account3));
    }

    @Test
    void making_Transactions_From_Different_Accounts_Getting_Most_Frequent_Beneficiary_Of_Account() {
        prepearing_TransactionManager_And_Accounts();
        Assertions.assertEquals(debitCard2, analyticsManager.mostFrequentBeneficiaryOfAccount(debitCard3));
    }

    @Before
    void prepearing_Accounts_Before_Getting_Top_Ten_Expensive_Purchases_Of_Account_Test() {
        debitCard1.addCash(10000);
        Transaction transaction1 = transactionManager.createTransaction(100, debitCard1, debitCard2);
        Transaction transaction2 = transactionManager.createTransaction(1200, debitCard1, debitCard2);
        Transaction transaction3 = transactionManager.createTransaction(300, debitCard1, debitCard2);
        Transaction transaction4 = transactionManager.createTransaction(1100, debitCard1, debitCard2);
        Transaction transaction5 = transactionManager.createTransaction(500, debitCard1, debitCard2);
        Transaction transaction6 = transactionManager.createTransaction(1600, debitCard1, debitCard2);
        Transaction transaction7 = transactionManager.createTransaction(1700, debitCard1, debitCard2);
        Transaction transaction8 = transactionManager.createTransaction(800, debitCard1, debitCard2);
        Transaction transaction9 = transactionManager.createTransaction(1900, debitCard1, debitCard2);
        Transaction transaction10 = transactionManager.createTransaction(1000, debitCard1, debitCard2);
        Transaction transaction11 = transactionManager.createTransaction(100, debitCard1, debitCard2);
        Transaction transaction12 = transactionManager.createTransaction(200, debitCard1, debitCard2);
        Transaction transaction13 = transactionManager.createTransaction(1100, debitCard2, debitCard1);

        for (Transaction transaction: analyticsManager.topTenExpensivePurchases(debitCard1)) {}
//        assertEquals(10, analyticsManager.topTenExpensivePurchases(account1));
    }

    @Test
    void getting_Top_Ten_Expensive_Purchases_Of_Account() {
        prepearing_Accounts_Before_Getting_Top_Ten_Expensive_Purchases_Of_Account_Test();
        Assertions.assertEquals(10, analyticsManager.topTenExpensivePurchases(debitCard1).size());
    }
}