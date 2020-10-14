import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

interface ReportGenerator<T> {
    Report generate(List<T> entities, ArrayList<Field> nameFields) throws IllegalAccessException;
}
