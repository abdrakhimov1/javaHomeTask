import java.util.ArrayList;
import java.util.HashMap;

public interface TransactionManagerFace {
    HashMap<Account, ArrayList<Transaction>> transactionMap = new HashMap<>();
}
