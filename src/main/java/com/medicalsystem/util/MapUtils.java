package com.medicalsystem.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static Map<String, String> of(String... args) {
        Map<String, String> map = new HashMap<>();

        if (args.length % 2 != 0)
            throw new IllegalArgumentException("Odd number of arguments");

        for (int i = 0; i < args.length - 1; i += 2)
            map.put(args[i], args[i + 1]);

        return map;
    }

}
