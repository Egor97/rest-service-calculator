package ru.bizyaev.egor.calculator.Caches;

import org.springframework.stereotype.Service;
import ru.bizyaev.egor.calculator.Entities.ExpressionEntity;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class Cache {
    private final HashMap<String, BigDecimal> cache = new HashMap<>();

    public void saveItem(ExpressionEntity expression, BigDecimal value) {
        cache.put(expression.toString(), value);
    }

    public HashMap<String, BigDecimal> getCache() {
        return cache;
    }

    @Override
    public String toString() {
        return "cache=" + cache;
    }
}
