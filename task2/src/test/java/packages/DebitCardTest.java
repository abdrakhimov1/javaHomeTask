package test.java.packages;

import junit.framework.TestCase;
import main.java.packages.DebitCard;
import main.java.packages.TransactionManager;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;


public class DebitCardTest {
    TransactionManager transactionManager = new TransactionManager();
    DebitCard debitCard = new DebitCard(transactionManager.getNewId(), transactionManager, 3);
    DebitCard debitCard2 = new DebitCard(transactionManager.getNewId(), transactionManager, 7);
    DebitCard templateCashDebitCard = new DebitCard(transactionManager.getNewId(), transactionManager, 0);
    DebitCard rollBackDebitCard = new DebitCard(transactionManager.getNewId(), transactionManager, 2);

    @Before
    public void add_money_to_all_test_accounts() {
        debitCard.addCash(500);
        debitCard2.addCash(500);
        templateCashDebitCard.addCash(600);
        rollBackDebitCard.addCash(1000);
    }

    @Test
    public void withdraw_money_from_account1_to_account2() {
        debitCard.withdraw(200, debitCard2);
        TestCase.assertEquals(300.0, debitCard.balanceOn(LocalDateTime.now()));
        TestCase.assertEquals(700.0, debitCard2.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void withdraw_money_into_cash() {
        templateCashDebitCard.withdrawCash(300);
        TestCase.assertEquals(300.0, templateCashDebitCard.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void add_cash_to_account_multiple_times(){
        add_money_to_all_test_accounts();
        debitCard.addCash(500);
        debitCard.addCash(500);
        TestCase.assertEquals(2000.0, debitCard.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void add_money_to_account() {
        add_money_to_all_test_accounts();
        debitCard.addCash(500);
        debitCard.add(600);
        TestCase.assertEquals(2100.0, debitCard.balanceOn(LocalDateTime.now()));
    }

    @Test
    public void call_history_method_check_its_length(){
        debitCard.addCash(300);
        int history1 = debitCard.history(LocalDateTime.now().minusSeconds(2), LocalDateTime.now()).size();
        assertEquals(2, history1);
    }

    @Test
    public void rolls_back_last_transaction() {
        rollBackDebitCard.addCash(300);
        rollBackDebitCard.rollbackLastTransaction();
        TestCase.assertEquals(1000.0, rollBackDebitCard.balanceOn(LocalDateTime.now()));
    }
}
