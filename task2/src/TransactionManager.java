import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Manages all transactions within the application
 */
public class TransactionManager implements TransactionManagerFace{
    /**
     * Creates and stores transactions
     * @param amount
     * @param originator
     * @param beneficiary
     * @return created Transaction
     */
    private static final AtomicLong acounter = new AtomicLong(0);

    public Transaction createTransaction(double amount,
                                         Account originator,
                                         Account beneficiary) {
        Transaction transaction = new Transaction(acounter.incrementAndGet(), amount, originator, beneficiary,false, false);
        if (transactionMap.get(originator) == null) {
            ArrayList<Transaction> transactionArrayList = new ArrayList<>();
            transactionArrayList.add(transaction);
            transactionMap.put(originator, transactionArrayList);
        } else {
            ArrayList<Transaction> transactionArrayList = transactionMap.get(originator);
            transactionArrayList.add(transaction);
            transactionMap.replace(originator, transactionArrayList);
        }
        return transaction;
    }

    public long getNewId(){
        return acounter.incrementAndGet();
    }

    public Collection<Transaction> findAllTransactionsByAccount(Account account) {
        return (Collection<Transaction>) transactionMap.get(account);
    }

    public Account findAccount(Long mostFrequentId) {
        for (Account account : transactionMap.keySet()) {
            if (account.getId() == mostFrequentId) {
                return account;
            }
        }
        return null;
    }

    public Transaction rollbackTransaction(Account account, long id) {
        ArrayList<Transaction> transactions = transactionMap.get(account);
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id ) {
                return transaction.rollback();
            }
        }
        return null;
    }
}