import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionManagerTest {

    TransactionManager transactionManager = new TransactionManager();
    Account account1 = new Account(123, transactionManager);
    Account account2 = new Account(124, transactionManager);

    @Before
    void making_transactions(){
        account1.addCash(2000);
    }

    @Test
    void checking_Length_Of_All_Transactions_By_Account() {
        Transaction transaction1 = transactionManager.createTransaction(100, account1,account2);
        Transaction transaction2 = transactionManager.createTransaction(200, account1,account2);
        Transaction transaction3 = transactionManager.createTransaction(100, account1,account2);
        Transaction transaction4 = transactionManager.createTransaction(100, account1,account2);
        Transaction transaction5 = transactionManager.createTransaction(100, account2,account1);
        assertEquals(4, transactionManager.findAllTransactionsByAccount(account1).size());
    }
}