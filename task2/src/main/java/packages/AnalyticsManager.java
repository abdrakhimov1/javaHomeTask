package main.java.packages;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public Optional<Entry> maxExpenseAmountEntryWithinInterval(List<Account> accounts, LocalDateTime from, LocalDateTime to){
        Comparator<Entry> entryComparator = Comparator.comparing(Entry::getAmount);
        return Optional.ofNullable(accounts
                .stream()
                .map(k -> k.history(from, to)
                        .stream().filter(x -> x.getAmount() < 0)
                        .collect(Collectors.toList())
                        .stream()
                        .max(entryComparator)).findFirst()
                .stream()
                .map(x -> x.stream().findFirst().orElse(null))
                .max(entryComparator).orElse(null));
    }

    public Account mostFrequentBeneficiaryOfAccount(DebitCard debitCard) {
        Collection<Transaction> allTransactions = transactionManager.findAllTransactionsByAccount(debitCard);
        return allTransactions
                .stream()
                .map(Transaction::getBeneficiary)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet()
                .stream().max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    public Collection<Transaction> topTenExpensivePurchases(DebitCard debitCard) {
        Collection<Transaction> allTransactions = transactionManager.findAllTransactionsByAccount(debitCard);
        Comparator<Transaction> comparator = Comparator.comparing(Transaction::getAmount);
        return allTransactions
                .stream()
                .sorted(comparator)
                .limit(10)
                .collect(Collectors.toList());
    }

    public double overallBalanceOfAccounts(List<Account> accounts) {
        return accounts
                .stream()
                .mapToDouble(k -> k.balanceOn(null))
                .sum();
    }

    public Set uniqueKeysOf(List accounts, KeyExtractor extractor) {
        return null;
    }

    public List accountsRangeFrom(List accounts, Account minAccount, Comparator comparator) {
        return null;
    }

}