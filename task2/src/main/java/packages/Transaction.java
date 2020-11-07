package main.java.packages;

import java.time.LocalDateTime;

public class Transaction {
    private long id;
    private final double amount;
    private final DebitCard originator;
    private final DebitCard beneficiary;
    private boolean executed;
    private boolean rolledBack;

    public Transaction(long id, double amount, DebitCard originator, DebitCard beneficiary, boolean executed, boolean rolledBack) {
        this.id = id;
        this.amount = amount;
        this.originator = originator;
        this.beneficiary = beneficiary;
        this.executed = executed;
        this.rolledBack = rolledBack;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public DebitCard getBeneficiary() {
        return beneficiary;
    }

    /**
     * Adding entries to both accounts
     * @throws IllegalStateException when was already executed
     */
    public Transaction execute() {
        LocalDateTime time = LocalDateTime.now();
        Entry entryOriginator = new Entry(this.originator,this, -this.amount, time);
        Entry entryBeneficiary = new Entry(this.beneficiary, this, this.amount, time);
        this.originator.addEntry(entryOriginator);
        this.beneficiary.addEntry(entryBeneficiary);
//        Вот тут сделал криво, но не знаю, как поправить. Если не копировать объект Entry, то он изменяется потом при добавлении в обычный аккаунт
        Entry bonusEntry = entryOriginator.entryClone();
        this.originator.getBonusAccount().addEntry(bonusEntry);
        this.executed = true;
        return this;
    }

    public boolean isRolledBack() {
        return rolledBack;
    }

    /**
     * Removes all entries of current transaction from originator and beneficiary
     * @throws IllegalStateException when was already rolled back
     */
    public Transaction rollback() {
        LocalDateTime time = LocalDateTime.now();
        Entry entryOriginator = new Entry(this.originator,this, -this.amount, time);
        Entry entryBeneficiary = new Entry(this.beneficiary, this, -this.amount, time);
        this.originator.getEntries().addEntry(entryOriginator);
        this.beneficiary.getEntries().addEntry(entryBeneficiary);
        this.rolledBack = true;
        return this;
    }
}