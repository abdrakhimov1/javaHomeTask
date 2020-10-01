import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

public class Account {
    private final long id;
    private final TransactionManager transactionManager;
    private final Entries entries;
    //private Account account;

    public Account(long id, TransactionManager transactionManager) {
        this.id = id;
        this.transactionManager = transactionManager;
        this.entries = new Entries();
        //this.account = new Account(id, transactionManager);
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
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
            LocalDateTime time = LocalDateTime.now();
            Transaction transaction = transactionManager.createTransaction(amount, this, beneficiary);
            Entry entry = new Entry(beneficiary, transaction, amount, time);
            entries.addEntry(entry);
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
            LocalDateTime time = LocalDateTime.now();
            Account cashAccount = new Account(id, transactionManager);
            Transaction transaction = new Transaction(id, amount, this, cashAccount, false, false);
            Entry entry = new Entry(this, transaction.execute(), amount, time);
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
            LocalDateTime time = LocalDateTime.now();
            long templateId = 123;
            Account cashAccount = new Account(templateId, transactionManager);
            Transaction transaction = transactionManager.createTransaction(amount,cashAccount,this);
            Entry entry = new Entry(this, transaction.execute(), amount, time);
            entries.addEntry(entry);
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
        if (amount > 0) {
            LocalDateTime time = LocalDateTime.now();
            Account beneficiary = this;
            Account account = new Account(id, transactionManager);
            Transaction transaction = transactionManager.createTransaction(amount, account, beneficiary);
            Entry entry = new Entry(beneficiary, transaction, amount, time);
            entries.addEntry(entry);
            return true;
        } else {
            return false;
        }
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
        for (Entry entry:entries.onDate(date)){
            if (entry.getAccount().equals(this)) {
                balanceOnDate += entry.getAmount();
            }
        }

        return balanceOnDate;
    }

    /**
     * Finds the last transaction of the account and rollbacks it
     */
    public void rollbackLastTransaction() {
        // write your code here
        //FIXME
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}










