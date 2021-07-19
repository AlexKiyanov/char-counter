package ru.kiianov.foxminded.charcounter.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheProvider extends LinkedHashMap<String, Map<String, Long>> {
    private static final long serialVersionUID = 1;
    private final int maxSize;

    public LRUCacheProvider(int capacity) {
        super(capacity, 0.75F, true);
        this.maxSize = capacity;
    }

    public boolean isPresent(String key) {
        return super.containsKey(key);
    }

    public void save(String key, Map<String, Long> value) {
        super.put(key, value);
    }

    public Map<String, Long> getValue(String key) {
        return super.get(key);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Map<String, Long>> eldest) {
        return this.size() > maxSize;
    }
}
