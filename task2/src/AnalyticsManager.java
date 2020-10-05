import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsManager {
    private final TransactionManager transactionManager;

    public AnalyticsManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Long mostFrequentBeneficiaryOfAccount(Account account) {
        Collection<Transaction> allTransactions = transactionManager.findAllTransactionsByAccount(account);
        ArrayList<Long> sequence = new ArrayList<Long>();
        for (Transaction transaction:allTransactions
             ) {
            sequence.add(transaction.getBeneficiary().getId());
        }
        Map<Long,Long> counts = sequence.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        Long mostFrequentId = counts.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        return mostFrequentId;
    }

    public Collection<Transaction> topTenExpensivePurchases(Account account) {
        Collection<Transaction> allTransactions = transactionManager.findAllTransactionsByAccount(account);
        TreeMap<Double, List<Transaction>> tree = new TreeMap<Double, List<Transaction>>(Collections.reverseOrder());
        for (Transaction transaction:allTransactions
             ) {
            double amount = transaction.getAmount();
            if (tree.get(amount) == null){
                List<Transaction> transactionList = new ArrayList<>();
                transactionList.add(transaction);
                tree.put(amount, transactionList);
            } else {
                tree.get(amount).add(transaction);
            }
        }
        Collection<Transaction> returnList = new ArrayList<>();
        for (List<Transaction> transactionList:tree.values()
             ) {
            for (Transaction transaction:transactionList
                 ) {
                if(returnList.size() == 10) {
                    return returnList;
                }
                returnList.add(transaction);
            }
        }
        return returnList;
    }
}
