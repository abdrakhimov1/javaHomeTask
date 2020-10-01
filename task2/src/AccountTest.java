import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private TransactionManager transactionManager = new TransactionManager();
    private static final AtomicLong acounter = new AtomicLong(0);
    Account account = new Account(acounter.incrementAndGet(),transactionManager);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.Test
    void withdraw() {
    }

    @org.junit.jupiter.api.Test
    void withdrawCash() {
    }

    @org.junit.jupiter.api.Test
    void addCash() {
        account.addCash(500);
        assertEquals(500, account.balanceOn(LocalDateTime.now()));
    }

    @org.junit.jupiter.api.Test
    void add() {
        account.add(600);

    }

    @org.junit.jupiter.api.Test
    void history() {
        int history1 = account.history(LocalDateTime.now(), LocalDateTime.now()).size();
//        assertEquals(2, history1);
    }

    @org.junit.jupiter.api.Test
    void balanceOn() {
    }

    @org.junit.jupiter.api.Test
    void rollbackLastTransaction() {
    }
}