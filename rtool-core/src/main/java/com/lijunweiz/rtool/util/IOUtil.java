package com.lijunweiz.rtool.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public final class IOUtil {

    private IOUtil() throws IllegalAccessException {
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
                throw new RuntimeException(e);
            }
        }
    }


}
