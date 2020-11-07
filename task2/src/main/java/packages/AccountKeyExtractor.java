package main.java.packages;

public class AccountKeyExtractor implements KeyExtractor<Integer, Account>{
    @Override
    public Integer extract(Account entity) {
        if (entity == null) return null;
        return entity.hashCode();
    }
}
