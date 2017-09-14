package com.medicalsystem.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtils {

    public static Map<String, String> ofStrings(String... args) {
        Map<String, String> map = new HashMap<>();

        if (args.length % 2 != 0)
            throw new IllegalArgumentException("Odd number ofStrings arguments");

        for (int i = 0; i < args.length - 1; i += 2)
            map.put(args[i], args[i + 1]);

        return map;
    }

    public static Map<Double, String> ofDoubles(List<Double> keys, List<String> values) {
        Map<Double, String> map = new HashMap<>();

        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Odd number of arguments");
        }

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }

    public static Map<Integer, String> ofIntegers(List<Integer> keys, List<String> values) {
        Map<Integer, String> map = new HashMap<>();

        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Odd number of arguments");
        }

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }
}
