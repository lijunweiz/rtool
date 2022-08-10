package com.lijunweiz.rtool.util;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class IOUtil {

    /**
     * 读取文件缓冲区大小
     */
    private static int DEFAULT_BUFFER_SIZE = 8192;

    private IOUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void close(InputStream in) throws IOException {
        closeable(in);
    }

    public static void close(OutputStream out) throws IOException {
        closeable(out);
    }

    private static void closeable(Closeable closeable) throws IOException {
        if (Objects.nonNull(closeable)) {
            closeable.close();
        }
    }

    /**
     * 文件拷贝
     * @param source 源文件路径
     * @param destination 拷贝的目的文件路径
     */
    public static void copy(String source, String destination) throws IOException {
        Objects.requireNonNull(source, "源文件路径不能为null");
        Objects.requireNonNull(destination, "目的文件路径不能为null");
        copy(new FileInputStream(source), new FileOutputStream(destination));
    }

    /**
     * 文件拷贝
     * @param source 源文件
     * @param destination 拷贝的目的文件
     */
    public static void copy(File source, File destination) throws IOException {
        Objects.requireNonNull(source, "源文件不能为null");
        Objects.requireNonNull(destination, "目的文件不能为null");

        copy(new FileInputStream(source), new FileOutputStream(destination));
    }

    /**
     * 文件流拷贝
     * @param source in流
     * @param destination out流
     */
    public static void copy(InputStream source, OutputStream destination) throws IOException {
        Objects.requireNonNull(source, "源文件流不能为null");
        Objects.requireNonNull(destination, "目的文件流不能为null");
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(source, DEFAULT_BUFFER_SIZE);
            out = new BufferedOutputStream(destination, DEFAULT_BUFFER_SIZE);
            int read = in.read();
            while (read != -1) {
                out.write(read);
                read = in.read();
            }
            out.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            close(in);
            close(out);
        }
    }

    /**
     * 读取文件成一个列表，其元素为string
     * @param file 文件路径
     * @return
     */
    public static List<String> readFile(String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 读取文件流成一个列表，其元素为string
     * @param in 输入流
     * @return
     */
    public static List<String> readFile(InputStream in) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }


}
