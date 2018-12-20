package com.focustest.highquality;

import java.util.*;

/**
 * focus Create in 10:31 2018/12/10
 */
public class Set2Map<K, V> extends HashSet<SimpleEntry<K, V>> {
    @Override
    public void clear() {
        super.clear();
    }

    public boolean containsKey(Object key) {
        return super.contains(new SimpleEntry<>(key, null));
    }

    public boolean containsValue(Object value) {
        for (SimpleEntry<K, V> simpleEntry : this) {
            if (simpleEntry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        for (SimpleEntry<K, V> simpleEntry : this) {
            if (simpleEntry.getKey().equals(key)) {
                return  simpleEntry.getValue();
            }
        }
        return null;
    }

    public V put(K key, V value) {
        add(new SimpleEntry<>(key, value));
        return value;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (K key : map.keySet()) {
            add(new SimpleEntry<>(key, map.get(key)));
        }
    }

    public V removeEntry(Object key) {
        for (Iterator<SimpleEntry<K, V>> it = this.iterator(); it.hasNext(); ) {
            SimpleEntry<K, V> simpleEntry = (SimpleEntry<K, V>)it.next();
            if (simpleEntry.getKey().equals(key)) {
                V value = simpleEntry.getValue();
                it.remove();
                return value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return super.size();
    }
}

class SimpleEntry<K, V> implements Map.Entry<K, V>, java.io.Serializable {
    private final K key;
    private V value;

    public SimpleEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public SimpleEntry(Map.Entry<? extends K, ? extends V> entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() == SimpleEntry.class) {
            SimpleEntry simpleEntry = (SimpleEntry)o;
            return simpleEntry.getKey().equals(getKey());
        }
        return false;
    }

    @Override
    public int hashCode() {
        Map map = new HashMap();
        Set set = new HashSet();
        Map treeMap = new TreeMap();
        return key == null ? 0 : key.hashCode();
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }
}
