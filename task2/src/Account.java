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

    public long getId() {
        return id;
    }

    public Entries getEntries() {
        return entries;
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
        if (balanceOn(LocalDateTime.now()) - amount > 0) {
            long cashID = transactionManager.getNewId();
            Account cashAccount = new Account(cashID, transactionManager);
            Transaction transaction = transactionManager.createTransaction(amount, this, cashAccount);
            transaction.execute();
            return true;
        }
        return false;
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
            long cashID = transactionManager.getNewId();
            Account cashAccount = new Account(cashID, transactionManager);
            Transaction transaction = transactionManager.createTransaction(amount, cashAccount, this);
            transaction.execute();
            return true;
        }
        return false;
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
        for (Entry entry : entries.onDate(date)){
            if (entry.getAccount() == this) {
                balanceOnDate += entry.getAmount();
            }
        }
        return balanceOnDate;
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Account otherAccount = (Account) object;
        if (id != ((Account) object).id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        return result;
    }

    /**
     * Finds the last transaction of the account and rollbacks it
     */
    public void rollbackLastTransaction() {
        entries.last().getTransaction().rollback();
    }

    public void addEntry(Entry entry) {
        entries.addEntry(entry);
    }
}
