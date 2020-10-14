import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static junit.framework.TestCase.assertEquals;


public class AccountTest {
    TransactionManager transactionManager = new TransactionManager();
    final AtomicLong acounter = new AtomicLong(0);
    Account account = new Account(acounter.incrementAndGet(), transactionManager);
    Account account2 = new Account(acounter.incrementAndGet(), transactionManager);
    Account templateCashAccount = new Account(acounter.incrementAndGet(), transactionManager);

    @Before
    public void add_money_to_all_test_accounts() {
        templateCashAccount.addCash(600);
        account.addCash(600);
        account.addCash(400);
    }

    @Test
    public void withdraw_money_from_account1_to_account2() {
        account.withdraw(200, account2);
        assertEquals(800.0, account.balanceOn(LocalDateTime.now()));
        assertEquals(200.0, account2.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void withdraw_money_into_cash() {
        templateCashAccount.withdrawCash(300);
        assertEquals(300.0, templateCashAccount.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void add_cash_to_account_multiple_times(){
        account.addCash(500);
        account.addCash(500);
        assertEquals(2000.0, account.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void add_money_to_account() {
        account.addCash(500);
        account.add(600);
        assertEquals(2100.0, account.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void call_history_method_check_its_length() throws InterruptedException {
        account.addCash(500);
        TimeUnit.SECONDS.sleep(1);
        account.addCash(500);
        TimeUnit.SECONDS.sleep(1);
        account.addCash(500);
        TimeUnit.SECONDS.sleep(1);
        int history1 = account.history(LocalDateTime.now().minusSeconds(2), LocalDateTime.now()).size();
        assertEquals(2, history1);
    }

    @Test
    public void adds_money_to_account_rolls_back_last_transaction() {
        account.rollbackLastTransaction();
        assertEquals(600.0, account.balanceOn(LocalDateTime.now()));
    }
}
