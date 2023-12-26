/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 限流工具类
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Component
public class FlowUtils {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * @Description: 限制blockTime内请求次数
     * @Param: [key, blockTime]
     * @return: boolean
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public boolean limitCheck(String key, int blockTime) {
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            return false;
        } else {
            stringRedisTemplate.opsForValue().set(key, "", blockTime, TimeUnit.SECONDS);
            return true;
        }
    }
}
