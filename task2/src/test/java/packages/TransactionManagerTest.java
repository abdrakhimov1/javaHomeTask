package test.java.packages;

import main.java.packages.DebitCard;
import main.java.packages.Transaction;
import main.java.packages.TransactionManager;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionManagerTest {

    TransactionManager transactionManager = new TransactionManager();
    DebitCard debitCard1 = new DebitCard(123, transactionManager, 3);
    DebitCard debitCard2 = new DebitCard(124, transactionManager, 2);

    @Before
    void making_transactions(){
        debitCard1.addCash(2000);
    }

    @Test
    void checking_Length_Of_All_Transactions_By_Account() {
        Transaction transaction1 = transactionManager.createTransaction(100, debitCard1, debitCard2);
        Transaction transaction2 = transactionManager.createTransaction(200, debitCard1, debitCard2);
        Transaction transaction3 = transactionManager.createTransaction(100, debitCard1, debitCard2);
        Transaction transaction4 = transactionManager.createTransaction(100, debitCard1, debitCard2);
        Transaction transaction5 = transactionManager.createTransaction(100, debitCard2, debitCard1);
        Assertions.assertEquals(4, transactionManager.findAllTransactionsByAccount(debitCard1).size());
    }
}