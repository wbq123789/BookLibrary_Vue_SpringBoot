/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

/**
 * @Description: 实体对象转换JSON工具类
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
public class JsonUtil {
    private static final JSONWriter.Feature[] features = {
            JSONWriter.Feature.WriteMapNullValue,
            // 输出空置字段
            JSONWriter.Feature.WriteNullListAsEmpty,
            // list字段如果为null，输出为[]，而不是null
            JSONWriter.Feature.WriteNullNumberAsZero,
            // 数值字段如果为null，输出为0，而不是null
            JSONWriter.Feature.WriteNullBooleanAsFalse,
            // Boolean字段如果为null，输出为false，而不是null
            JSONWriter.Feature.WriteNullStringAsEmpty,
            // 字符类型字段如果为null，输出为""，而不是null
    };

    public static String toJson(Object object) {
        return JSON.toJSONString(object, features);
    }
}