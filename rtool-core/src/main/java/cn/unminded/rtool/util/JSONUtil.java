package cn.unminded.rtool.util;

import cn.unminded.rtool.core.RuleException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Objects;

/**
 * json 工具类
 */
public final class JSONUtil {

    private JSONUtil() {
        throw new UnsupportedOperationException();
    }

    public static final ObjectMapper DEFAULT_OBJECT_MAPPER;

    static {
        DEFAULT_OBJECT_MAPPER = JsonMapper
                .builder()
                .addModules(new JavaTimeModule())
                .build();
    }

    /**
     * 转换一个对象成json字符串
     * @param object 待处理对象
     * @return
     */
    public static String toJSONString(Object object) {
        try {
            return DEFAULT_OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuleException("obj to json string error ", e);
        }
    }

    /**
     * 转换一个对象成json字符串
     * @param object 待处理对象
     * @param first 对象转换成json字符串时的第一个转换特征
     * @param others 对象转换成json字符串时的其他转换特征
     * @return
     */
    public static String toJSONString(Object object, SerializationFeature first, SerializationFeature ... others) {
        Objects.requireNonNull(first);

        try {
            if (Objects.isNull(others)) {
                return DEFAULT_OBJECT_MAPPER.writer(first).writeValueAsString(object);
            } else {
                return DEFAULT_OBJECT_MAPPER.writer(first, others).writeValueAsString(object);
            }
        } catch (JsonProcessingException e) {
            throw new RuleException("obj to json string error ", e);
        }
    }

    /**
     * 转换json字符串成一个指定类型的对象
     * @param text 带转换字符串
     * @param clazz
     * @param <T> 指定的对象类型
     * @return
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        Objects.requireNonNull(text);
        Objects.requireNonNull(clazz);

        try {
            return DEFAULT_OBJECT_MAPPER.readValue(text, clazz);
        } catch (JsonProcessingException e) {
            throw new RuleException("json string to obj error ", e);
        }
    }

    /**
     * 转换json字符串成一个指定类型的对象
     * @param text 带转换字符串
     * @param typeReference 通过类型应用，指定转换集合类型
     * @param <T> 指定的对象类型
     * @return
     */
    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        Objects.requireNonNull(text);
        Objects.requireNonNull(typeReference);

        try {
            return DEFAULT_OBJECT_MAPPER.readValue(text, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuleException("json string to obj error ", e);
        }
    }

    /**
     * 转换一个对象成一个指定类型的对象
     * @param obj 一个对象
     * @param clazz
     * @param <T> 指定的对象类型
     * @return
     */
    public static <T> T convert(Object obj, Class<T> clazz) {
        Objects.requireNonNull(obj);
        Objects.requireNonNull(clazz);

        try {
            return DEFAULT_OBJECT_MAPPER.readValue(toJSONString(obj), clazz);
        } catch (JsonProcessingException e) {
            throw new RuleException("json string to obj error ", e);
        }
    }

}
