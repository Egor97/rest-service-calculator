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
        boolean check = getCache().stream().anyMatch(x -> x.getKey().equals(expression));
        if (check) {
            item = getCache().stream().filter(x -> x.getKey().equals(expression))
                             .findFirst()
                             .orElse(null);
        }
        return check;
    }

    public BigDecimal getSaveResult() {
        int index = getCache().indexOf(item);
        return getCache().get(index).getValue();
    }

    public Pair<String, BigDecimal> getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "cache=" + list;
    }
}
