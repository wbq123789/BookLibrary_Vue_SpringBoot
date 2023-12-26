/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.view_obj.response;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 用户信息返回实体
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Data
public class AuthorizeViewObj {
    String username;
    String role;
    String token; //JWT 令牌
    Date expire;
}
