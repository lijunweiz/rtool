package com.lijw.decision.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public final class IOUtils {

    private static Logger logger = LoggerFactory.getLogger(IOUtils.class);

    private IOUtils() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void close(InputStream is) {
        closeable(is);
    }

    public static void close(OutputStream os) {
        closeable(os);
    }

    private static void closeable(Closeable closeable) {
        if (Objects.nonNull(closeable)) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("流关闭异常", e);
            }
        }
    }


}
