import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainGenerator {
    public static void main(String[] args) throws IllegalAccessException, IOException {
        Person person1 = new Person("TestName1", 35);
        Person person2 = new Person("TestName2", 40);
        Person person3 = new Person("TestName3", 45);
        ReportGeneratorImpl<Person> personReportGenerator = new ReportGeneratorImpl<>(Person.class);
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personReportGenerator.tabSeparatedGenerator(personList).writeTo(System.out);
    }
}
