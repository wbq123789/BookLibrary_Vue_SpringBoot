package booklibrary.library_backend.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class FlowUtils {

    @Resource
    StringRedisTemplate stringRedisTemplate;
    public boolean limitCheck(String key,int blockTime){
        if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            return false;
        }
        else {
            stringRedisTemplate.opsForValue().set(key, "", blockTime, TimeUnit.SECONDS);
            return true;
        }
    }
}
