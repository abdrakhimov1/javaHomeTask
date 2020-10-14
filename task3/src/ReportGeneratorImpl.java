import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReportGeneratorImpl<T> implements ReportGenerator<T> {

    Class<T> tClass;
    FieldGetter fieldGetter = new FieldGetter();

    public ReportGeneratorImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    public Report tabSeparatedGenerator(List<T> entities) throws IllegalAccessException {
        if (entities == null || entities.isEmpty()) return new ReportImpl(new byte[0]);
        ArrayList<Field> nameFields = fieldGetter.getReportedFieldsForClass(tClass);
        return generate(entities, nameFields);
    }

    @Override
    public Report generate(List<T> entities, ArrayList<Field> nameFields) throws IllegalAccessException {
        if (entities == null || entities.isEmpty()) return new ReportImpl(new byte[0]);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.join("\t", fieldGetter.getReportedFieldsForClass(entities.get(0).getClass()).toString()));

        ArrayList<String> fieldValues = new ArrayList<>();

        for (T entry : entities) {
            int i = 0;
            for (Field field: nameFields
                 ) {
                field.setAccessible(true);
                fieldValues.add(i, field.get(entry).toString());
                i++;
            };
            stringBuilder.append("\n");
            stringBuilder.append(String.join("\t", fieldValues));
            fieldValues.clear();
        }

        return new ReportImpl(stringBuilder.toString().getBytes());
    }
}
