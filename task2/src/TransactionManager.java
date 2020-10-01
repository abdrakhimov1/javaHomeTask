import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Manages all transactions within the application
 */


public class TransactionManager {
    /**
     * Creates and stores transactions
     * @param amount
     * @param originator
     * @param beneficiary
     * @return created Transaction
     */
    private static final AtomicLong acounter = new AtomicLong(0);
    HashMap<Account, ArrayList<Transaction>> transactionHashMap = new HashMap<>();

    public Transaction createTransaction(double amount,
                                         Account originator,
                                         Account beneficiary) {
        Transaction transaction = new Transaction(acounter.incrementAndGet(), amount, originator, beneficiary,false, false);
        if (transactionHashMap.containsKey(originator)) {
            ArrayList<Transaction> tmpTransaction = transactionHashMap.get(originator);
            tmpTransaction.add(transaction.execute());
            transactionHashMap.replace(originator, tmpTransaction);
        } else {
            ArrayList tmpArray = new ArrayList();
            tmpArray.add(transaction);
            transactionHashMap.put(originator, tmpArray);
        }
        return transaction;
    }

    public Collection<Transaction> findAllTransactionsByAccount(Account account) {
        return (Collection<Transaction>) transactionHashMap.get(account);
    }

    public void rollbackTransaction(Transaction transaction) {
        transaction.rollback();
    }

    public void executeTransaction(Transaction transaction) {
        transaction.execute();
    }
}