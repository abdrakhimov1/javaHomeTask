import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static junit.framework.TestCase.assertEquals;


public class AccountTest {
    TransactionManager transactionManager = new TransactionManager();
    Account account = new Account(transactionManager.getNewId(), transactionManager);
    Account account2 = new Account(transactionManager.getNewId(), transactionManager);
    Account templateCashAccount = new Account(transactionManager.getNewId(), transactionManager);
    Account rollBackAccount = new Account(transactionManager.getNewId(), transactionManager);

    @Before
    public void add_money_to_all_test_accounts() {
        account.addCash(500);
        account2.addCash(500);
        templateCashAccount.addCash(600);
        rollBackAccount.addCash(1000);
    }

    @Test
    public void withdraw_money_from_account1_to_account2() {
        account.withdraw(200, account2);
        assertEquals(300.0, account.balanceOn(LocalDateTime.now()));
        assertEquals(700.0, account2.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void withdraw_money_into_cash() {
        templateCashAccount.withdrawCash(300);
        assertEquals(300.0, templateCashAccount.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void add_cash_to_account_multiple_times(){
        add_money_to_all_test_accounts();
        account.addCash(500);
        account.addCash(500);
        assertEquals(2000.0, account.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void add_money_to_account() {
        add_money_to_all_test_accounts();
        account.addCash(500);
        account.add(600);
        assertEquals(2100.0, account.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void call_history_method_check_its_length(){
        account.addCash(300);
        int history1 = account.history(LocalDateTime.now().minusSeconds(2), LocalDateTime.now()).size();
        assertEquals(2, history1);
    }

    @Test
    public void rolls_back_last_transaction() {
        rollBackAccount.addCash(300);
        rollBackAccount.rollbackLastTransaction();
        assertEquals(1000.0, rollBackAccount.balanceOn(LocalDateTime.now()));
    }
}
