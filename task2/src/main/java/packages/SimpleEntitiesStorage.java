package main.java.packages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEntitiesStorage<EntityType> implements BankEntitiesStorage<EntityType>{
    private final Map<Object, EntityType> storage = new HashMap<>();
    private final KeyExtractor keyExtractor;

    public SimpleEntitiesStorage(KeyExtractor<?, ? super EntityType> keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    @Override
    public <TYPE extends EntityType>void save(TYPE entity) {
        storage.put(keyExtractor.extract(entity), entity);
    }

    @Override
    public void saveAll(List<? extends EntityType> entities) {
        for (EntityType entity : entities
             ) {
            save(entity);
        }
    }

    @Override
    public EntityType findByKey(Object key) {
        return storage.get(key);
    }

    @Override
    public List<EntityType> findAll() {
        return (List<EntityType>) storage.values();
    }

    @Override
    public void deleteByKey(Object key) {
        storage.remove(key);
    }

    @Override
    public void deleteAll(List<EntityType> entities) {
        storage.values().removeAll(entities);
    }
}
