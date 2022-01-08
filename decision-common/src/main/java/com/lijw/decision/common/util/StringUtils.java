package com.lijw.decision.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StringUtils {

    /** 大写转为小写cache */
    private final static Map<Character, Character> UPPER_TO_LOWER = new HashMap<>();

    static {
        UPPER_TO_LOWER.put('A', 'a');
        UPPER_TO_LOWER.put('B', 'b');
        UPPER_TO_LOWER.put('C', 'c');
        UPPER_TO_LOWER.put('D', 'd');
        UPPER_TO_LOWER.put('E', 'e');
        UPPER_TO_LOWER.put('F', 'f');
        UPPER_TO_LOWER.put('G', 'g');
        UPPER_TO_LOWER.put('H', 'h');
        UPPER_TO_LOWER.put('I', 'i');
        UPPER_TO_LOWER.put('J', 'j');
        UPPER_TO_LOWER.put('K', 'k');
        UPPER_TO_LOWER.put('L', 'l');
        UPPER_TO_LOWER.put('M', 'm');
        UPPER_TO_LOWER.put('N', 'n');
        UPPER_TO_LOWER.put('O', 'o');
        UPPER_TO_LOWER.put('P', 'p');
        UPPER_TO_LOWER.put('Q', 'q');
        UPPER_TO_LOWER.put('R', 'r');
        UPPER_TO_LOWER.put('S', 's');
        UPPER_TO_LOWER.put('T', 't');
        UPPER_TO_LOWER.put('U', 'u');
        UPPER_TO_LOWER.put('V', 'v');
        UPPER_TO_LOWER.put('W', 'w');
        UPPER_TO_LOWER.put('X', 'x');
        UPPER_TO_LOWER.put('Y', 'y');
        UPPER_TO_LOWER.put('Z', 'z');
    }

    public static final String EMPTY_STRING = "";

    /**
     * 把第一个字符转为小写
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> String getCamelName(Class<T> clazz) {
        if (Objects.isNull(clazz)) {
            return "null";
        }

        return UPPER_TO_LOWER.get(clazz.getSimpleName().charAt(0)) + clazz.getSimpleName().substring(1);
    }

    public static <T> String getCamelName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            return "null";
        }
        if (UPPER_TO_LOWER.containsKey(name.charAt(0))) {
            return UPPER_TO_LOWER.get(name.charAt(0)) + name.substring(1);
        }

        return name;
    }

}
