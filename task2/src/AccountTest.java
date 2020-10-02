import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private TransactionManager transactionManager = new TransactionManager();
    private static final AtomicLong acounter = new AtomicLong(0);
    Account account = new Account(acounter.incrementAndGet(),transactionManager);
    Account account2 = new Account(acounter.incrementAndGet(),transactionManager);

    @org.junit.jupiter.api.Test
    void addMoneyToAccountThenWithdrawMoneyFromAccount1ToAccount2() {
        account.addCash(600);
        account.withdraw(200, account2);
        assertEquals(400, account.balanceOn(LocalDateTime.now()));
        assertEquals(200, account2.balanceOn(LocalDateTime.now()));
    }

    @org.junit.jupiter.api.Test
    void addMoneyToAccountThenWithdrawMoneyIntoCash() {
        account.addCash(600);
        account.withdrawCash(300);
        assertEquals(300, account.balanceOn(LocalDateTime.now()));
    }

    @org.junit.jupiter.api.Test
    void addCashToAccountMultipleTimes() throws InterruptedException {
        account.addCash(500);
        account.addCash(500);
        account.addCash(500);
        assertEquals(1500, account.balanceOn(LocalDateTime.now()));
    }

    @org.junit.jupiter.api.Test
    void addMoneyToAccount() {
        account.addCash(500);
        account.add(600);
        assertEquals(1100, account.balanceOn(LocalDateTime.now()));
    }

    @org.junit.jupiter.api.Test
    void callHistoryMethodCheckItsLength() throws InterruptedException {
        account.addCash(500);
        TimeUnit.SECONDS.sleep(1);
        account.addCash(500);
        TimeUnit.SECONDS.sleep(1);
        account.addCash(500);
        TimeUnit.SECONDS.sleep(1);
        int history1 = account.history(LocalDateTime.now().minusSeconds(2), LocalDateTime.now()).size();
        assertEquals(2, history1);
    }

    @org.junit.jupiter.api.Test
    void addsMoneyToAccountRollsBackLastTransaction() {
        account.addCash(500);
        account.addCash(500);
        account.addCash(500);
        account.rollbackLastTransaction();
        assertEquals(1000, account.balanceOn(LocalDateTime.now()));
    }
}
