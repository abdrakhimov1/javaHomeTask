import java.lang.reflect.Field;
import java.util.ArrayList;

public class FieldGetter {
    public ArrayList<Field> getReportedFieldsForClass(Class<?> tClass) {
        ArrayList<Field> fields = new ArrayList<>();
            for (var field : tClass.getDeclaredFields())
                fields.add(field);
        return fields;
    }
}
