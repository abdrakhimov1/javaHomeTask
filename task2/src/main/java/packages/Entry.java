package main.java.packages;

import java.time.LocalDateTime;

/**
 * The record of allocating the amount to the account
 * Amount can be either positive or negative depending on originator or beneficiary
 */
public class Entry{
    private final Account debitCard;
    private final Transaction transaction;
    private double amount;
    private final LocalDateTime time;

    public Entry(Account debitCard, Transaction transaction, double amount, LocalDateTime time) {
        this.debitCard = debitCard;
        this.transaction = transaction;
        this.amount = amount;
        this.time = time;
    }

    public Entry entryClone(){
        return new Entry(this.debitCard, this.transaction, this.amount, this.time);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return debitCard;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTime() {
        return time;
    }


}