package main.java.packages;

import java.util.*;

public class AnalyticsManager {
    private final TransactionManager transactionManager;
    Queue<Transaction> transactionQueue = new PriorityQueue<>(10, amountComparator);


    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public static Comparator<Transaction> amountComparator = new Comparator<Transaction>() {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return (int) (o1.getAmount() - o2.getAmount());
        }
    };

    public Account mostFrequentBeneficiaryOfAccount(DebitCard debitCard) {
        Collection<Transaction> allTransactions = transactionManager.findAllTransactionsByAccount(debitCard);
        Map<Account, Integer> sequence = new HashMap<>();
        int mostFreq = 0;
        Account returnDebitCard = null;
        for (Transaction transaction:allTransactions) {
            Account benificary = transaction.getBeneficiary();
            if (sequence.containsKey(benificary)){
                int templateNumber = sequence.get(benificary);
                sequence.replace(benificary, templateNumber, templateNumber + 1);
                if (mostFreq < templateNumber + 1){
                    mostFreq = templateNumber + 1;
                    returnDebitCard = benificary;
                }
            } else {
                sequence.put(benificary, 1);
                if (mostFreq < 1) {
                    mostFreq = 1;
                    returnDebitCard = benificary;
                }
            }
        }
        return returnDebitCard;
    }

    public Collection<Transaction> topTenExpensivePurchases(DebitCard debitCard) {
        Collection<Transaction> allTransactions = transactionManager.findAllTransactionsByAccount(debitCard);
        for (Transaction transaction:allTransactions
        ) {
            if (transactionQueue.size() < 10) {
                transactionQueue.add(transaction);
            } else {
                if (transactionQueue.peek().getAmount() < transaction.getAmount()){
                    transactionQueue.poll();
                    transactionQueue.add(transaction);
                }
            }
        }
        return transactionQueue;
    }

    public double overallBalanceOfAccounts(List<Account> accounts) {
        double amount = 0;
        for (Account account : accounts) {
            amount += account.balanceOn(null);
        }
        return amount;
    }

    public Set uniqueKeysOf(List accounts, KeyExtractor extractor) {
        return null;
    }

    public List accountsRangeFrom(List accounts, Account minAccount, Comparator comparator) {
        return null;
    }

}