/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebConfiguration {
    /**
     * @Description: 引入密码加密编码器
     * @Param: null
     * @return: org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @Bean
    BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
