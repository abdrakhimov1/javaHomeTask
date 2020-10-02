import java.time.LocalDateTime;

public class Transaction {
    private final long id;
    private final double amount;
    private final Account originator;
    private final Account beneficiary;
    private boolean executed;
    private boolean rolledBack;

    public Transaction(long id, double amount, Account originator, Account beneficiary, boolean executed, boolean rolledBack) {
        this.id = id;
        this.amount = amount;
        this.originator = originator;
        this.beneficiary = beneficiary;
        this.executed = executed;
        this.rolledBack = rolledBack;
    }

    public double getAmount() {
        return amount;
    }

    public Account getBeneficiary() {
        return beneficiary;
    }

    /**
     * Adding entries to both accounts
     * @throws IllegalStateException when was already executed
     */
    public void execute() {
        this.executed = true;
        LocalDateTime time = LocalDateTime.now();
        Entry entryOriginator = new Entry(this.originator,this, this.amount, time);
        Entry entryBeneficiary = new Entry(this.beneficiary, this, this.amount, time);
        this.originator.getEntries().addEntry(entryOriginator);
        this.beneficiary.getEntries().addEntry(entryBeneficiary);
    }

    public void cashExecution() {
        this.executed = true;
        LocalDateTime time = LocalDateTime.now();
        Entry entryOriginator = new Entry(this.originator, this, this.amount, time);
        this.originator.getEntries().addEntry(entryOriginator);
    }

    public boolean isRolledBack() {
        return rolledBack;
    }

    /**
     * Removes all entries of current transaction from originator and beneficiary
     * @throws IllegalStateException when was already rolled back
     */
    public void rollback() {
        this.rolledBack = true;
    }

    public void addCashExecution() {
        this.executed = true;
        LocalDateTime time = LocalDateTime.now();
        Entry entryBeneficiary = new Entry(this.beneficiary, this, this.amount, time);
        this.beneficiary.getEntries().addEntry(entryBeneficiary);
    }
}