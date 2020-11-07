package main.java.packages;

import java.util.ArrayList;
import java.util.HashMap;

public interface TransactionManagerFace {
    HashMap<DebitCard, ArrayList<Transaction>> transactionMap = new HashMap<>();
}
