package test.java.packages;

import main.java.packages.Transaction;
import main.java.packages.TransactionKeyExtractor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionKeyExtractorTest {

    @Test
    void extract() {
        Transaction transaction = new Transaction(12, 214, null, null, false, false);
        Long key = new TransactionKeyExtractor().extract(transaction);
        assertEquals(transaction.getId(), key);
    }
}