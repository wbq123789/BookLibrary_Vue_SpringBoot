/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.utils;

/**
 * @Description: 固定字段
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
public class Const {
    //JWT黑名单
    public static final String JWT_BLACK_LIST = "jwt:blacklist:";
    //邮箱验证码限制时间
    public static final String VERIFY_EMAIL_LIMIT = "verify:email:limit";
    //邮箱验证码
    public static final String VERIFY_EMAIL_DATA = "verify:email:data";
    //Security FilterChain 优先级
    public static final int ORDER_CORS = -102;
}
