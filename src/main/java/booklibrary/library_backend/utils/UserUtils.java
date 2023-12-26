/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.utils;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
* @Description: User工具类
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Component
public class UserUtils {
    /**
    * @Description: 获取请求头JWT令牌中的用户信息
    * @Param: [jwt]
    * @return: org.springframework.security.core.userdetails.UserDetails
    * @Author: 王贝强
    * @Date: 2023/12/26
    */
    public UserDetails toUser(DecodedJWT jwt) {
        Map<String, Claim> claims = jwt.getClaims();
        return User
                .withUsername(claims.get("name").toString())
                .password("***")
                .authorities(claims.get("authorities").asArray(String.class))
                .build();
    }
    /**
    * @Description: 获取请求头JWT令牌中的用户Id
    * @Param: [jwt]
    * @return: Integer
    * @Author: 王贝强
    * @Date: 2023/12/26
    */
    public Integer toId(DecodedJWT jwt) {
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get("id").asInt();
    }
}
