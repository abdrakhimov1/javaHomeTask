import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Account {
    private final long id;
    private final TransactionManager transactionManager;
    private final Entries entries;

    public Account(long id, TransactionManager transactionManager) {
        this.id = id;
        this.transactionManager = transactionManager;
        this.entries = new Entries();
    }

    public Entries getEntries() {
        return entries;
    }

    public long getId() {
        return id;
    }

    /**
     * Withdraws money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdraw(double amount, Account beneficiary) {
        if (amount > 0 && balanceOn(LocalDateTime.now()) - amount > 0) {
            Transaction transaction = transactionManager.createTransaction(amount, this, beneficiary);
            transaction.execute();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Withdraws cash money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdrawCash(double amount) {
        if (amount > 0 && balanceOn(LocalDateTime.now()) - amount > 0) {
            TransactionManager transactionManager = new TransactionManager();
            long cashID = -1;
            Account cashAccount = new Account(cashID, transactionManager);
            Transaction transaction = transactionManager.createTransaction(amount, this, cashAccount);
            transaction.cashExecution();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds cash money to account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean addCash(double amount) {
        if (amount > 0) {
            TransactionManager transactionManager = new TransactionManager();
            long cashID = -1;
            Account cashAccount = new Account(cashID, transactionManager);
            Transaction transaction = transactionManager.createTransaction(amount, cashAccount, this);
            transaction.addCashExecution();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds money to account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean add(double amount) {
        return addCash(amount);
    }

    public Collection<Entry> history(LocalDateTime from, LocalDateTime to) {
            return entries.betweenDates(from, to);
    }

    /**
     * Calculates balance on the accounting entries basis
     * @param date
     * @return balance
     */

    public double balanceOn(LocalDateTime date) {
        double balanceOnDate = 0;
        System.out.println();
        for (Entry entry:entries.onDate(date)){
            if (entry.getAccount()==this) {
                if(!entry.getTransaction().isRolledBack()) {
                    if (entry.getTransaction().getBeneficiary() == this) {
                        balanceOnDate += entry.getAmount();
                    } else {
                        balanceOnDate -= entry.getAmount();
                    }
                }

            }
        }
        return balanceOnDate;
    }

    /**
     * Finds the last transaction of the account and rollbacks it
     */
    public void rollbackLastTransaction() {
        entries.last().getTransaction().rollback();
    }
}










