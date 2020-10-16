import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;

/**
 * Collection of entries for the account. Use it to save and get history of payments
 */
public class Entries implements EntriesFace{

    Queue<Entry> entries;

    public Entries() {
        this.entries  = new ArrayDeque<>();
    }

    void addEntry(Entry entry) {
        entries.add(entry);
    }

    Collection<Entry> betweenDates(LocalDateTime from, LocalDateTime to) {
        if (entries.isEmpty()){
            return (entries);
        }
        ArrayList<Entry> returnList = new ArrayList<>();
        for (Entry entry : entries) {
            LocalDate todayDate = entry.getTime().toLocalDate();
            if (!from.toLocalDate().isAfter(todayDate) && !to.toLocalDate().isBefore(todayDate)){
                returnList.add(entry);
            }
        }
        return returnList;
    }

    Collection<Entry> onDate(LocalDateTime on) {
        if (entries.isEmpty()){
            return (entries);
        }
        ArrayList<Entry> returnList = new ArrayList<>();
        for (Entry entry : entries) {
            LocalDate todayDate = entry.getTime().toLocalDate();
            if (todayDate.isEqual(on.toLocalDate())){
                returnList.add(entry);
            }
        }
        return returnList;
    }

    Entry last() {
        List tmpList = new ArrayList(entries);
        Entry returnEntry = (Entry) tmpList.get(tmpList.size() - 1);
        return returnEntry;
    }
}