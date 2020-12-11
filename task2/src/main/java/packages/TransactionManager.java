package main.java.packages;

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
     * @return created main.java.packages.Transaction
     */
    private static final AtomicLong acounter = new AtomicLong(0);

    public Transaction createTransaction(double amount,
                                         DebitCard originator,
                                         DebitCard beneficiary) {
        Transaction transaction = new Transaction(acounter.incrementAndGet(), amount, originator, beneficiary,false, false);
//        это круто
        transactionMap.computeIfPresent(originator, (key, value) -> returnFullTransactionList(value, transaction));
        transactionMap.computeIfAbsent(originator, k -> returnTransactionList(transaction));
        return transaction;
    }

    public long getNewId(){
        return acounter.incrementAndGet();
    }

    public Collection<Transaction> findAllTransactionsByAccount(DebitCard debitCard) {
        return (Collection<Transaction>) transactionMap.get(debitCard);
    }

    public DebitCard findAccount(Long mostFrequentId) {
        for (DebitCard debitCard : transactionMap.keySet()) {
            if (debitCard.getId() == mostFrequentId) {
                return debitCard;
            }
        }
        return null;
    }

    public Transaction rollbackTransaction(DebitCard debitCard, long id) {
        ArrayList<Transaction> transactions = transactionMap.get(debitCard);
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id ) {
                return transaction.rollback();
            }
        }
        return null;
    }

    public ArrayList<Transaction> returnTransactionList(Transaction transaction) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);
        return transactions;
    }

    public ArrayList<Transaction> returnFullTransactionList(ArrayList<Transaction> transactions, Transaction transaction) {
        transactions.add(transaction);
        return transactions;
    }
}