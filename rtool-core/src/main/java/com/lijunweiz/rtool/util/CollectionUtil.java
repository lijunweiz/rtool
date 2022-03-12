package com.lijunweiz.rtool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class CollectionUtil {

    private CollectionUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <K, V> boolean isNullOrEmpty(Map<K, V> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    /**
     * 判断对象不是null也不是empty, 只对Collection Map有效，其他返回false
     * @param obj 待判断对象
     * @return 如果不是null也不是empty 返回true, 否则返回false
     */
    public static boolean notNullAndEmpty(Object obj) {
        if (obj instanceof Collection) {
            Collection<?> collection = Collection.class.cast(obj);
            return !isNullOrEmpty(collection);
        } else if (obj instanceof Map) {
            Map<?, ?> map = Map.class.cast(obj);
            return !isNullOrEmpty(map);
        } else {
            return false;
        }
    }

}
