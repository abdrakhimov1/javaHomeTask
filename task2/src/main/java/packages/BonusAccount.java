package main.java.packages;

import java.time.LocalDateTime;

public class BonusAccount implements Account{
    private final TransactionManager transactionManager;
    private final Entries entries;
    private final int bonusPercent;

    public BonusAccount(TransactionManager transactionManager, Entries entries, int bonusPercent) {;
        this.transactionManager = transactionManager;
        this.entries = entries;
        this.bonusPercent = bonusPercent;
    }

    @Override
    public double balanceOn(LocalDateTime date) {
        double balanceOnDate = 0;
        for (Entry entry : entries.onDate(date)) {
            if (entry.getAccount() == this) {
                balanceOnDate += entry.getAmount();
            }
        }
        return balanceOnDate;
    }

    @Override
    public void addEntry(Entry entry) {
        entry.setAmount(entry.getAmount() * bonusPercent/100);
        entries.addEntry(entry);
    }
}
