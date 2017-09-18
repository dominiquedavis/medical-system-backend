package com.medicalsystem.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtils {

    public static <T> Map<T, T> of(T... args) {
        Map<T, T> map = new HashMap<>();

        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Odd number of arguments");
        }

        for (int i = 0; i < args.length - 1; i += 2) {
            map.put(args[i], args[i + 1]);
        }

        return map;
    }

    public static <K, V> Map<K, V> of(List<K> keys, List<V> values) {
        Map<K, V> map = new HashMap<>();

        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Odd number of arguments");
        }

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }

}
