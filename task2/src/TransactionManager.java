import java.util.*;
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
        if (transactionHashMap.get(originator) == null) {
            ArrayList<Transaction> transactionArrayList = new ArrayList<>();
            transactionArrayList.add(transaction);
            transactionHashMap.put(originator, transactionArrayList);
        } else {
            ArrayList<Transaction> transactionArrayList = transactionHashMap.get(originator);
            transactionArrayList.add(transaction);
            transactionHashMap.replace(originator, transactionArrayList);
        }
        return transaction;
    }

    public long getNewId(){
        return acounter.incrementAndGet();
    }

    public Collection<Transaction> findAllTransactionsByAccount(Account account) {
        return (Collection<Transaction>) transactionHashMap.get(account);
    }

    public Account findAccount(Long mostFrequentId) {
        for (Account account : transactionHashMap.keySet()) {
            if (account.getId() == mostFrequentId) {
                return account;
            }
        }
        return null;
    }
}