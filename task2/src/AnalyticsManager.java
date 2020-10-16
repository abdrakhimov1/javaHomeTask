import java.time.LocalDateTime;
import java.util.*;
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

    public Account mostFrequentBeneficiaryOfAccount(Account account) {
        Collection<Transaction> allTransactions = transactionManager.findAllTransactionsByAccount(account);
        Map<Account, Integer> sequence = new HashMap<>();
        int mostFreq = 0;
        Account returnAccount = null; 
        for (Transaction transaction:allTransactions) {
            Account benificary = transaction.getBeneficiary();
            if (sequence.containsKey(benificary)){
                int templateNumber = sequence.get(benificary);
                sequence.replace(benificary, templateNumber, templateNumber + 1);
                if (mostFreq < templateNumber + 1){
                    mostFreq = templateNumber + 1;
                    returnAccount = benificary;
                }
            } else {
                sequence.put(benificary, 1);
                if (mostFreq < 1) {
                    mostFreq = 1;
                    returnAccount = benificary;
                }
            }
        }
        return returnAccount;
    }

    public Collection<Transaction> topTenExpensivePurchases(Account account) {
        Collection<Transaction> allTransactions = transactionManager.findAllTransactionsByAccount(account);
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
}