package cn.unminded.rtool.util;

import java.util.Objects;

public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    public static final String EMPTY = "";
    public static final String STR_NULL = "null";

    /**
     * 把第一个字符转为小写
     * @param clazz
     * @return
     */
    public static String getFirstCharLowerName(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return STR_NULL;
        }

        return charToLower(clazz.getSimpleName().charAt(0)) + clazz.getSimpleName().substring(1);
    }

    /**
     * 把第一个字符转为小写
     * @param name
     * @return
     */
    public static String getFirstCharLowerName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            return STR_NULL;
        }

        return charToLower(name.charAt(0)) + name.substring(1);
    }

    /**
     * 把第一个字符转为大写
     * @param name
     * @return
     */
    public static String getFirstCharUpperName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            return STR_NULL;
        }

        return charToUpper(name.charAt(0)) + name.substring(1);
    }

    /**
     * 给定字符变为大写
     * @param ch
     * @return
     */
    public static char charToUpper(char ch) {
        if (ch >= 'a' && ch<= 'z') {
            return (char) (ch - 32);
        }

        return ch;
    }

    /**
     * 给定字符变为小写
     * @param ch
     * @return
     */
    public static char charToLower(char ch) {
        if (ch >= 'A' && ch<= 'Z') {
            return (char) (ch + 32);
        }

        return ch;
    }

    /**
     * 判断字符是null或者是empty
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return Objects.isNull(str) || str.trim().isEmpty();
    }

    /**
     * 判断字符不是null也不是empty
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
