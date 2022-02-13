package com.lijw.decision.core.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json 工具类
 */
public final class JsonUtil {

    public static String toJSONString(Object object) {
        return toJSONString(object, SerializerFeature.WriteMapNullValue);
    }

    public static String toJSONString(Object object, SerializerFeature... features) {
        return JSONObject.toJSONString(object, features);
    }

    public static Object toJson(Object object) {
        return toJson(object, SerializeConfig.getGlobalInstance());
    }

    public static Object toJson(Object object, SerializeConfig serializeConfig) {
        return JSONObject.toJSON(object, serializeConfig);
    }

    public static Object parse(String text) {
        return JSONObject.parse(text);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSONObject.parseObject(text, clazz);
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        return JSONObject.parseObject(text, typeReference);
    }

}
