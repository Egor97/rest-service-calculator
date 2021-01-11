package ru.bizyaev.egor.calculator.Caches;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;

@Service
public class Cache {
    @Value("${cache.size}")
    private int size;
    private final LinkedList<Pair<String, BigDecimal>> list = new LinkedList<>();
    Pair<String, BigDecimal> item;

    public void saveResult(String expression, BigDecimal value) {
        item = new Pair<>(expression, value);
        if (list.size() >= size) {
            list.removeLast();
        }
        list.addFirst(item);
    }

    private LinkedList<Pair<String, BigDecimal>> getCache() {
        return list;
    }

    public boolean checkCache(String expression) {
        return getCache().stream().anyMatch(x -> x.getKey().equals(expression));
    }

    public BigDecimal getSaveResult() {
        int index = getCache().indexOf(item);
        return getCache().get(index).getValue();
    }

    @Override
    public String toString() {
        return "cache=" + list;
    }
}
