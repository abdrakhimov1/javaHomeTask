package main.java.packages;

import java.util.List;

public interface BankEntitiesStorage<EntityType> {
    <T extends EntityType>void save(T entity);
    void saveAll(List<? extends EntityType> entities);
    EntityType findByKey(Object key);
    List<EntityType> findAll();
    void deleteByKey(Object key);
    void deleteAll(List<EntityType> entities);
}
