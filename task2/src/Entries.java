import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Collection of entries for the account. Use it to save and get history of payments
 */
public class Entries implements EntriesFace{
    
    private int totalNumberOfEntries = 0;

    void addEntry(Entry entry) {
        entriesMap.put(entry.getTime(), totalNumberOfEntries);
        entries.add(entry);
        totalNumberOfEntries += 1;
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
        TreeMap<LocalDateTime, Integer> tmpMap = new TreeMap<>(entriesMap);
        int from1 = tmpMap.get(tmpMap.headMap(from, true).lastKey());
        int to1 = tmpMap.get(tmpMap.headMap(to, true).lastKey());
        return (Collection<Entry>) entries.subList(from1, to1+1);
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private LocalDateTime getDateNearest(List<Entry> entries, Date targetDate){
        for (Entry entry : entries) {
            if (convertToDateViaSqlTimestamp(entry.getTime()).compareTo(targetDate) <= 0) return entry.getTime();
        }
        return convertToLocalDateTimeViaInstant(targetDate);
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    Collection<Entry> onDate(LocalDateTime on) {
        Date date1 = convertToDateViaSqlTimestamp(entries.get(0).getTime());
        Date date2 = convertToDateViaSqlTimestamp(on);
        if (entriesMap.isEmpty()){
            return (entries);
        }
        TreeMap<LocalDateTime, Integer> tmpMap = new TreeMap<>(entriesMap);
        int tmp1 = tmpMap.get(tmpMap.headMap(on, true).lastKey());
        return (Collection<Entry>) entries.subList(0, tmp1+1);
    }

    Entry last() {
        return entries.get(totalNumberOfEntries-1);
    }
}