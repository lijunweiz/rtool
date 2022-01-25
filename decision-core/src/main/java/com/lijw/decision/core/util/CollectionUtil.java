package com.lijw.decision.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public final class CollectionUtil {

    private static Logger logger = LoggerFactory.getLogger(CollectionUtil.class);

    private CollectionUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

}
