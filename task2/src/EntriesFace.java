import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface EntriesFace {
    HashMap<LocalDateTime, Integer> entriesMap = new HashMap<>();
    ArrayList<Entry> entries = new ArrayList<>();
}
