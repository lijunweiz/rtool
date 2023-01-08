package cn.unminded.rtool.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class IOUtil {

    /**
     * 读取文件缓冲区大小
     */
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private IOUtil() {
        throw new UnsupportedOperationException();
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
        copy(new FileInputStream(source), new FileOutputStream(destination));
    }

    /**
     * 文件拷贝
     * @param source 源文件
     * @param destination 拷贝的目的文件
     */
    public static void copy(File source, File destination) throws IOException {
        copy(new FileInputStream(source), new FileOutputStream(destination));
    }

    /**
     * 文件流拷贝
     * @param source in流
     * @param destination out流
     */
    public static void copy(InputStream source, OutputStream destination) throws IOException {
        Objects.requireNonNull(source, "source can't be null");
        Objects.requireNonNull(destination, "destination can't be null");

        try (BufferedInputStream in = new BufferedInputStream(source, DEFAULT_BUFFER_SIZE);
             BufferedOutputStream out = new BufferedOutputStream(destination, DEFAULT_BUFFER_SIZE)) {
            int read = in.read();
            while (read != -1) {
                out.write(read);
                read = in.read();
            }
            out.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 读取文件成一个列表，其元素为string
     * @param file 文件路径
     * @return
     */
    public static List<String> readFile(String file) throws IOException {
        return readFile(file, Charset.defaultCharset());
    }

    /**
     * 读取文件成一个列表，其元素为string
     * @param file 文件路径
     * @return
     */
    public static List<String> readFile(String file, Charset charset) throws IOException {
        Objects.requireNonNull(file, "file can't be null");
        Objects.requireNonNull(charset, "charset can't be null");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset))) {
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
    public static List<String> readFile(InputStream in) {
        if (Objects.isNull(in)) {
            return Collections.emptyList();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.lines().collect(Collectors.toList());
    }

    /**
     * 读取流成一个字节数组
     * @param in 输入流
     * @return
     */
    public static byte[] readByte(InputStream in) throws IOException {
        if (Objects.isNull(in)) {
            return new byte[0];
        }

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int read = in.read(buffer);
        while (read != -1) {
            result.write(buffer);
            read = in.read(buffer);
        }
        result.flush();
        result.close();

        return result.toByteArray();
    }

    /**
     * 读取输入流成为一个字符串 默认编码{@link StandardCharsets#UTF_8}
     * @param in 输入流
     * @return
     */
    public static String readString(InputStream in) throws IOException {
        if (Objects.isNull(in)) {
            return StringUtil.EMPTY;
        }

        byte[] bytes = readByte(in);
        return bytes.length == 0 ? StringUtil.EMPTY : new String(bytes, StandardCharsets.UTF_8).trim();
    }

    /**
     * 读取输入流成为一个字符串
     * @param in 输入流
     * @param charset 指定的字符串编码
     * @return
     */
    public static String readString(InputStream in, Charset charset) throws IOException {
        if (Objects.isNull(in)) {
            return StringUtil.EMPTY;
        }

        byte[] bytes = readByte(in);
        return bytes.length == 0 ? StringUtil.EMPTY : new String(bytes, charset).trim();
    }

}
