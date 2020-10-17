package main.java.packages;

public class TransactionKeyExtractor implements KeyExtractor<Long, Transaction>{
    @Override
    public <TYPE extends Transaction> Long extract(TYPE entity) {
        return entity.getId();
    }
}
