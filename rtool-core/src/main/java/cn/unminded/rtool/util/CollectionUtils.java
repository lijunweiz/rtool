package cn.unminded.rtool.util;

import java.util.*;

public class CollectionUtils {

    private CollectionUtils() {
        throw new UnsupportedOperationException();
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    /**
     * 判断对象不是null也不是empty, 只对Collection Map String有效，其他返回false
     * @param obj 待判断对象
     * @return 如果不是null也不是empty 返回true, 否则返回false
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean isNotEmpty(Object obj) {
        if (Objects.isNull(obj)) {
            return false;
        } else if (obj instanceof Collection) {
            Collection<?> collection = (Collection) obj;
            return !isEmpty(collection);
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            return !isEmpty(map);
        } else if (obj.getClass().isArray()) {
            return true;
        } else if (obj instanceof String) {
            return ((String) obj).isEmpty();
        } else {
            return false;
        }
    }

    /**
     * 两个list合并
     * @param left 第一个list
     * @param right 第二个list
     * @param <T> list中元素类型
     * @return 合并后的list
     */
    public static <T> List<T> merge(List<T> left, List<T> right) {
        if (isEmpty(left)) {
            return right;
        } else if (isEmpty(right)) {
            return left;
        } else {
            List<T> list = new ArrayList<>();
            list.addAll(left);
            list.addAll(right);
            return list;
        }
    }

    /**
     * 两个map合并
     * @param left 第一个map
     * @param right 第二个map
     * @param <K> map中键类型
     * @param <V> map中元素类型
     * @return 合并后的map
     */
    public static <K, V> Map<K, V> merge(Map<K, V> left, Map<K, V> right) {
        if (isEmpty(left)) {
            return right;
        } else if (isEmpty(right)) {
            return left;
        } else {
            Map<K, V> map = new HashMap<>();
            map.putAll(left);
            map.putAll(right);
            return map;
        }
    }

}
