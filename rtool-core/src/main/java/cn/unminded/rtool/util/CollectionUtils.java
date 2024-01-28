package cn.unminded.rtool.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

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
    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof Collection) {
            Collection<?> collection = (Collection) obj;
            return !isEmpty(collection);
        } else if (obj instanceof Map) {
            Map<?, ?> map = (Map) obj;
            return !isEmpty(map);
        } else if (obj instanceof Object[]) {
            return ((Object[]) obj).length > 0;
        } else if (obj instanceof String) {
            return ((String) obj).isEmpty();
        } else {
            return false;
        }
    }

}
