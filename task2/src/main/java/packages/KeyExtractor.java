package main.java.packages;

public interface KeyExtractor<KEY, VALUE> {
        <TYPE extends VALUE> KEY extract(TYPE entity);
}
