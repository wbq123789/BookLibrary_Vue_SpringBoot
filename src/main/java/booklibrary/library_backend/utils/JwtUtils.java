/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: JWT令牌工具类
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Component
public class JwtUtils {

    @Value("${spring.security.jwt.key}")
    String key;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Value("${spring.security.jwt.expire}")
    int expire;

    /**
     * @Description: 通过用户信息创建JWT令牌
     * @Param: [details, id, username]
     * @return: String
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public String CreateJwt(UserDetails details, int id, String username) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        Date expireTime = this.expireTime();
        return JWT
                .create()
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("id", id)
                .withClaim("name", username)
                .withClaim("authorities", details.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withExpiresAt(expireTime)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * @Description: 从请求头中解析JWT令牌
     * @Param: [HeaderToken]
     * @return: com.auth0.jwt.interfaces.DecodedJWT
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public DecodedJWT resolveJwt(String HeaderToken) {
        String token = this.convertToken(HeaderToken);
        if (token == null) return null;
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            DecodedJWT jwt = jwtVerifier.verify(token);
            if (this.isValidateToken(jwt.getId()))
                return null;
            Date expiresAT = jwt.getExpiresAt();
            return new Date().after(expiresAT) ? null : jwt;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    /**
     * @Description: 使JWT令牌失效
     * @Param: [HeaderToken]
     * @return: boolean
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public boolean inValidateJwt(String HeaderToken) {
        String token = this.convertToken(HeaderToken);
        if (token == null) return false;
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            DecodedJWT jwt = jwtVerifier.verify(token);
            String id = jwt.getId();
            return deleteToken(id, jwt.getExpiresAt());
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * @Description: 将Token列入Redis黑名单
     * @Param: [uuid, time]
     * @return: boolean
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    private boolean deleteToken(String uuid, Date time) {
        if (this.isValidateToken(uuid)) return false;
        Date now_date = new Date();
        long expire = Math.max(time.getTime() - now_date.getTime(), 0);
        stringRedisTemplate.opsForValue().set(Const.JWT_BLACK_LIST + uuid, "", expire, TimeUnit.MILLISECONDS);
        return true;
    }

    /**
     * @Description: 验证Token是否被列入Redis黑名单
     * @Param: [uuid]
     * @return: boolean
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    private boolean isValidateToken(String uuid) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(Const.JWT_BLACK_LIST + uuid));
    }

    /**
     * @Description: 设置过期时间
     * @Param: null
     * @return: java.util.Date
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    public Date expireTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expire * 24);
        return calendar.getTime();
    }

    /**
     * @Description: 校验并转换请求头中的Token令牌
     * @Param: [HeaderToken]
     * @return: String
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    private String convertToken(String HeaderToken) {
        if (HeaderToken == null || !HeaderToken.startsWith("Bearer "))
            return null;
        return HeaderToken.substring(7);
    }
}
