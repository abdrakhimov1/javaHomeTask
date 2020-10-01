import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Collection of entries for the account. Use it to save and get history of payments
 */
public class Entries {

    private HashMap<LocalDateTime, Integer> entriesMap = new HashMap<>();
    private ArrayList<Entry> entries = new ArrayList<>();
    private int totalNumberOfEntries = 0;

    void addEntry(Entry entry) {
        if (!entriesMap.isEmpty()) {
            entriesMap.put(entry.getTime(), totalNumberOfEntries);
            entries.add(entry);
            totalNumberOfEntries += 1;
        } else {
            entriesMap.put(entry.getTime(), totalNumberOfEntries);
            entries.add(entry);
            totalNumberOfEntries += 1;
        }
    }

    Collection<Entry> from(LocalDate date) {
        if (entriesMap.isEmpty()){
            return (entries);
        }
        return (Collection<Entry>) entries.get(entriesMap.get(date));
    }

    Collection<Entry> betweenDates(LocalDateTime from, LocalDateTime to) {
        if (entriesMap.isEmpty()){
            return (entries);
        }
        return (Collection<Entry>) entries.subList(entriesMap.get(from), entriesMap.get(to));
    }

    Collection<Entry> onDate(LocalDateTime on) {
        if (entriesMap.isEmpty()){
            return (entries);
        }
        return (Collection<Entry>) entries.subList(0, entriesMap.get(on)+1);
    }

    Entry last() {
        return entries.get(totalNumberOfEntries);
    }
}